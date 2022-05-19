package com.salama.test.ws.util;

public class ServiceUtil {

	private static int ServerNum = (1 << 24) & 0xFF000000;

	private final static int DATA_ID_SEQ_MIN = 0;
	private final static int DATA_ID_SEQ_MAX = 0x01000000;
	private static int DataIdSeq = DATA_ID_SEQ_MIN;
	private static Object LockForDataId = new Object();

	public static String newDataID() {
		synchronized (LockForDataId) {
			if(DataIdSeq == DATA_ID_SEQ_MAX) {
				DataIdSeq = DATA_ID_SEQ_MIN + 1;
			} else {
				DataIdSeq ++;
			}

			int dataIdSeqAndServerNum = ServerNum | DataIdSeq;

			return HexUtil.toHexString(dataIdSeqAndServerNum) 
					+ HexUtil.toHexString(System.currentTimeMillis());
		}
	}

}