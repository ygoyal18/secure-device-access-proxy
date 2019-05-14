// ----------------------------------------------------------------------------
//   The confidential and proprietary information contained in this file may
//   only be used by a person authorized under and to the extent permitted
//   by a subsisting licensing agreement from ARM Limited or its affiliates.
//
//          (C)COPYRIGHT 2018 ARM Limited or its affiliates.
//              ALL RIGHTS RESERVED
//
//   This entire notice must be reproduced on all copies of this file
//   and copies of this file may only be made by a person if such person is
//   permitted to do so under the terms of a subsisting license agreement
//   from ARM Limited or its affiliates.
// ----------------------------------------------------------------------------
package com.arm.mbed.sda.proxysdk.protocol;

import com.upokecenter.cbor.CBORObject;

public class OperationResponse extends ResponseBase {

	private byte[] blob = null;
	
    public OperationResponse(MessageIn msg) {
        super(msg);
        if (getResponseStatus() == ProtocolConstants.STATUS_OK) {
            CBORObject blobCbor = msg.cborPayload.get(ResponseFieldEnum.FIELD_BLOB.getValueAsCbor());
            if (null != blobCbor) {
            	try {
            		this.blob = blobCbor.GetByteString();
            	} catch(Exception ex) {
            		throw new ProtocolException("Encoded response message blob is invalid: " + ex.getMessage());
            	}
            }
        }
    }

    public byte[] getBlob() {
        if (getResponseStatus() != ProtocolConstants.STATUS_OK) {
            throw new ProtocolException("Encoded response message status is: " + getResponseStatus());
        }
    	return this.blob;
    }
}