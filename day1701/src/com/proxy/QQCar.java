package com.proxy;

public class QQCar implements Car{

	@Override
	public void run() {
		System.out.println("qq run");
	}

	@Override
	public void stop() {
		System.out.println("qq 刹车");
	}

}
