package util;

import kong.unirest.*;
import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.util.Optional;

/**
 * API 호출 클래스
 */
public class RestClient {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RestClient.class);
	// private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final boolean DEBUG_MODE = true;
	private static boolean initialized = false;
	private final String baseUrl;
	private final String apiKey;
	private final String secretKey;

	public static synchronized RestClient of(String baseUrl, String apiKey, String secretKey) throws GeneralSecurityException {
		if (!initialized) {
			Unirest.config().socketTimeout(2000).connectTimeout(2000).concurrency(10, 5).followRedirects(false).enableCookieManagement(true);
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
		return (T) request.header("X-Timestamp", timestamp).header("X-API-KEY", apiKey).header("X-Customer", String.valueOf(customerId)).header("X-Signature", Signatures.of(timestamp, request.getHttpMethod().name(), path, secretKey));
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
			log.error("## Error Status:" + _response.getStatus() + ", " + Optional.ofNullable(_response.getBody()).map(body -> body.toString()).orElse("None"));
			_response.getParsingError().ifPresent(e -> {
				log.error("## Parsing Exception:", e);
				log.error("## Original body:" + e.getOriginalBody());
				log.error("## Error Message:" + e.getMessage());
			});
		});
		int status = response.getStatus();
		if (status / 100 != 2 && status != 204) {
			String transactionId = response.getHeaders().getFirst("X-Transaction-ID");
			String message = response.getStatus() + " " + response.getStatusText() + ". X-Transaction-ID: " + response.getHeaders().getFirst("X-Transaction-ID") + ", Response Body: " + Optional.ofNullable(response.getBody()).map(body -> body.toString()).orElse("None");
			message += response.getParsingError().map(e -> {
				log.error("error", e);
				return e.getOriginalBody();
			}).orElse("");
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

	public String getBaseUrl() {
		return this.baseUrl;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof RestClient)) return false;
		final RestClient other = (RestClient) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$baseUrl = this.getBaseUrl();
		final Object other$baseUrl = other.getBaseUrl();
		if (this$baseUrl == null ? other$baseUrl != null : !this$baseUrl.equals(other$baseUrl)) return false;
		final Object this$apiKey = this.getApiKey();
		final Object other$apiKey = other.getApiKey();
		if (this$apiKey == null ? other$apiKey != null : !this$apiKey.equals(other$apiKey)) return false;
		final Object this$secretKey = this.getSecretKey();
		final Object other$secretKey = other.getSecretKey();
		if (this$secretKey == null ? other$secretKey != null : !this$secretKey.equals(other$secretKey)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof RestClient;
	}

	@Override
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $baseUrl = this.getBaseUrl();
		result = result * PRIME + ($baseUrl == null ? 43 : $baseUrl.hashCode());
		final Object $apiKey = this.getApiKey();
		result = result * PRIME + ($apiKey == null ? 43 : $apiKey.hashCode());
		final Object $secretKey = this.getSecretKey();
		result = result * PRIME + ($secretKey == null ? 43 : $secretKey.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "RestClient(baseUrl=" + this.getBaseUrl() + ", apiKey=" + this.getApiKey() + ", secretKey=" + this.getSecretKey() + ")";
	}

	private RestClient(final String baseUrl, final String apiKey, final String secretKey) {
		this.baseUrl = baseUrl;
		this.apiKey = apiKey;
		this.secretKey = secretKey;
	}
}
