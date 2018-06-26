package com.xmhx.buzz.utils;

/**
 * 类 名 称： UUIDUtil
 * 类 描 述： 
 * 创 建 人： wujin
 * 创建时间： 2013-6-28 下午02:54:29
 * 
 */
public class UUIDUtils {
	private final long workerId;
	private final static long twepoch = 1361753741828L;
	private long sequence = 0L;
	private final static long workerIdBits = 4L;
	public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	private final static long sequenceBits = 10L;

	private final long workerIdShift = sequenceBits;
	private final static long timestampLeftShift = sequenceBits + workerIdBits;
	public final long sequenceMask = -1L ^ -1L << sequenceBits;

	private long lastTimestamp = -1L;
	public static UUIDUtils uudIDUtil = null;

	public static UUIDUtils getSingerUuidUtil(){
		if(uudIDUtil == null){
			uudIDUtil = new UUIDUtils();
		}
		return uudIDUtil ;
	}
	
	private UUIDUtils() {
		final long workId  = 12;
		this.workerId = workId;
	}
	
	private synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & this.sequenceMask;
			if (this.sequence == 0) {
				System.out.println("###########" + sequenceMask);
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			try {
				throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.lastTimestamp = timestamp;
		long nextId = ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << this.workerIdShift) | (this.sequence);
		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
	
	public static long getLongTypeUUID(){
        return UUIDUtils.getSingerUuidUtil().nextId();
	}
	
	public static String getStringTypeUUID(){
		return String.valueOf(UUIDUtils.getSingerUuidUtil().nextId());
	}
	
	public static void main(String[] args) {
		System.out.println(getStringTypeUUID() + " -- " + getLongTypeUUID());
	}

}
