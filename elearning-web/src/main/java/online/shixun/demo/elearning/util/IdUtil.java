package online.shixun.demo.elearning.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IdUtil {
    protected static final Logger log = LoggerFactory.getLogger(IdUtil.class);
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long maxWorkerId;
    private long maxDatacenterId;
    private long sequenceBits;
    private long workerIdShift;
    private long datacenterIdShift;
    private long timestampLeftShift;
    private long sequenceMask;
    private long lastTimestamp;

    /**
     * 自动生成不重复的int类型的id
     * @return
     */
    public synchronized long nextId() {
        long timestamp = TimeUtil.timeGen();
        if (timestamp < this.lastTimestamp) {
            log.error(String.format("clock is moving backwards.  Rejecting requests until %d.", this.lastTimestamp));
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        } else {
            if (this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & this.sequenceMask;
                if (this.sequence == 0L) {
                    timestamp = TimeUtil.tilNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            return timestamp << (int) this.timestampLeftShift | this.datacenterId << (int) this.datacenterIdShift | this.workerId << (int) this.workerIdShift | this.sequence;
        }
    }
}

/**
 * 时间处理
 */
class TimeUtil {
    public static long twepoch = 1288834974657L;

    public TimeUtil() {
    }

    public static long timeGen() {
        return System.currentTimeMillis() - twepoch;
    }

    public static long timeGen(Date date) {
        return date.getTime() - twepoch;
    }

    public static long timeGen(long time) {
        return time - twepoch;
    }

    public static long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = timeGen(); timestamp <= lastTimestamp; timestamp = timeGen()) {
            ;
        }

        return timestamp;
    }
}