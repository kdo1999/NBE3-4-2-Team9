package com.backend.domain.jobskill.constant;

import lombok.Getter;

@Getter
public enum JobSkillConstant {
	JOB_SKILL_REDIS_KEY("job_skill_key:");
	private final String key;

	JobSkillConstant(String key) {
		this.key = key;
	}
}
