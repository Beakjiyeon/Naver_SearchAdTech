package common;

/**
 * 검수 상태 상수
 * */
public enum InspectStatus {
	UNDER_REVIEW(10), 
	APPROVED(20), 
	PENDING(30); 

	private final Integer value;

	private InspectStatus(Integer value) {
		this.value = value;
	}
}
