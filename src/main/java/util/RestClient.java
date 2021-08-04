package util;

import kong.unirest.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.util.Optional;

/**
 * API 호출 클래스
 * */
@Data
@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestClient {

    // private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final boolean DEBUG_MODE = true;

	private static boolean initialized = false;

	private final String baseUrl;
	private final String apiKey;
	private final String secretKey;

	public static synchronized RestClient of(String baseUrl, String apiKey, String secretKey) throws GeneralSecurityException {
		if (!initialized) {
			Unirest.config()
					.socketTimeout(2000)
					.connectTimeout(2000)
					.concurrency(10, 5)
					.followRedirects(false)
					.enableCookieManagement(true);
			initialized = true;
		}
		return new RestClient(baseUrl, apiKey, secretKey);
	}

	public GetRequest get(String path, long customerId) throws SignatureException {
		return withAuthInfo(Unirest.get(baseUrl + path), path, customerId, apiKey, secretKey);
	}

	public HttpRequestWithBody post(String path, long customerId) throws SignatureException {
		HttpRequestWithBody request = Unirest.post(baseUrl + path);
		request.header("Content-Type", "application/json;charset=UTF-8");
		return withAuthInfo(request, path, customerId, apiKey, secretKey);
	}

	public HttpRequestWithBody delete(String path, long customerId) throws SignatureException {
		log.debug("## Remove :" + path);
		return withAuthInfo(Unirest.delete(baseUrl + path), path, customerId, apiKey, secretKey);
	}

	public HttpRequestWithBody put(String path, long customerId) throws SignatureException {
		HttpRequestWithBody request = Unirest.put(baseUrl + path);
		request.header("Content-Type", "application/json;charset=UTF-8");
		return withAuthInfo(request, path, customerId, apiKey, secretKey);
	}

	@SuppressWarnings("unchecked")
	private <T extends HttpRequest> T withAuthInfo(T request, String path, long customerId, String apiKey, String secretKey) throws SignatureException {
		String timestamp = String.valueOf(System.currentTimeMillis());

		return (T) request
				.header("X-Timestamp", timestamp)
				.header("X-API-KEY", apiKey)
				.header("X-Customer", String.valueOf(customerId))
				.header("X-Signature", Signatures.of(timestamp, request.getHttpMethod().name(), path, secretKey));
	}

	public void asEmpty(HttpResponse response) {
		int status = response.getStatus();
		if (status / 100 != 2 && status != 204) {
			throw new UnirestException("asEmpty() is error, " + response.getStatus());
		} else if (status == 204) {
			log.warn("asEmpty 204 No Content");
		}
		return;
	}

	public <T> Optional<T> asObject(HttpResponse<T> response) throws Exception {

		response.ifFailure(_response -> {
			log.error("## Error Status:" + _response.getStatus()
					+ ", " + Optional.ofNullable(_response.getBody()).map(body->body.toString()).orElse("None"));
			_response.getParsingError().ifPresent(e -> {
				log.error("## Parsing Exception:", e);
				log.error("## Original body:" + e.getOriginalBody());
				log.error("## Error Message:" + e.getMessage());
			});
		});

		int status = response.getStatus();
		if (status / 100 != 2 && status != 204) {
			String transactionId = response.getHeaders().getFirst("X-Transaction-ID");
			String message = response.getStatus() + " " + response.getStatusText()
					+ ". X-Transaction-ID: " + response.getHeaders().getFirst("X-Transaction-ID")
					+ ", Response Body: " + Optional.ofNullable(response.getBody()).map(body->body.toString()).orElse("None");
			message += response.getParsingError().map(e->{log.error("error",e); return e.getOriginalBody();}).orElse("");
			log.error(message);
			throw new UnirestException("## respose error:" + status + ", " + response.getStatusText());
		} else if (status == 204) {
			log.warn("## asObject 204 No Content");
		} else {
			// pass
		}

		T responseBody = response.ifFailure(Error.class, r -> {
			Error e = r.getBody();
			log.error("## result parsing error. " + e.getMessage(), e);

			throw new UnirestException(e);
		}).getBody();
		return Optional.ofNullable(responseBody);
	}
}
