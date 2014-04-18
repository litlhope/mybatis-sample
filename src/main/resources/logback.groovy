import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.filter.EvaluatorFilter
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

def HOSTNAME = hostname // scope 룰 때문에 nested block에서는 hostname 변수 사용불가

appender('console', ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = "▶ %-5level %d{HH:mm:ss.SSS} [${HOSTNAME}] [%thread] %logger{36} - %msg%n"
	}
}

appender('daily', RollingFileAppender) {
	file = "logs/daily.log"
	append = true

	rollingPolicy(TimeBasedRollingPolicy) {
		fileNamePattern = "/logs/daily.log.%d{yyyy-MM-dd}.gz" // 자동 gz 압축
		maxHistory = 10 // 10일간만 보관
	}

	encoder(PatternLayoutEncoder) {
		pattern = '%-5level %d{yyyy-MM-dd HH:mm:ss} [%thread] %logger{36} - %msg%n'
	}

	// 특정 레벨 이상만 로깅
	filter(ch.qos.logback.classic.filter.ThresholdFilter) {
		level = INFO // INFO 이상 레벨만 로깅
	}

	// 마커가 필요할 때
	filter(EvaluatorFilter) {
		evaluator(ch.qos.logback.classic.boolex.OnMarkerEvaluator) {
			marker = '마커이름'
		}
		onMismatch = DENY
		onMatch = NEUTRAL
	}
}

//appender('fixedWindowRollingFile', RollingFileAppender) {
//	file = 'logs/fixedWindowRollingFile.log'
//	append = true
//
//	encoder(PatternLayoutEncoder) {
//		pattern = '%-5level %d{yyyy-MM-dd HH:mm:ss} [%thread] %logger{36} - %msg%n'
//	}
//
//	rollingPolicy(ch.qos.logback.core.rolling.FixedWindowRollingPolicy) {
//		fileNamePattern = 'logs/fixedWindowRollingFile.log.%i'
//		minIndex = 1 // xx.log.1 ~ xx.log.5
//		maxIndex = 5
//	}
//
//	triggeringPolicy(ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy) {
//		maxFileSize = '100MB' // 파일 크기 100MB될때마다 롤링
//	}
}

root(INFO, ['console', 'daily'])
