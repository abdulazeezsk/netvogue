package org.netvogue.server.aws.core;

import com.amazonaws.services.s3.model.ProgressEvent;
import com.amazonaws.services.s3.model.ProgressListener;

public class NetvogueProgressListener implements ProgressListener {

	@Override
	public void progressChanged(ProgressEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getEventCode());
	}

}
