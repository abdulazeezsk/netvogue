package org.netvogue.server.aws.core;

public enum BucketName {
	DEV("veawe-dev"),
	PRI("veawe-private"),
	PROD("netvogueprod"),
	TEST("netvoguetest");
	
	private String name;
	BucketName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
