/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jundaonet.makemoney.utilities;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 
 * @author zhaohengyi
 */
final public class StdRandom {

	/**
	 * �������0��1֮���ʵ�� [0,1)
	 * 
	 * @return double �����
	 */
	public static double uniform() {
		Random random = new SecureRandom();
		return random.nextDouble();
	}

	/**
	 * �������0��N-1֮������� [0,N)
	 * 
	 * @param N
	 *            ����
	 * @return int �����
	 */
	public static int uniform(int N) {
		Random random = new SecureRandom();
		return random.nextInt(N);
	}

	/**
	 * �������0��1֮���ʵ�� [0,1)
	 * 
	 * @return double �����
	 */
	public static double random() {
		return uniform();
	}

	/**
	 * �������a��b-1֮������� [a,b)
	 * 
	 * @param a
	 *            ����
	 * @param b
	 *            ����
	 * @return int �����
	 */
	public static int uniform(int a, int b) {
		return a + uniform(b - a);
	}

	/**
	 * �������a��b֮���ʵ��
	 * 
	 * @param a
	 *            ����
	 * @param b
	 *            ����
	 * @return double �����
	 */
	public static double uniform(double a, double b) {
		return a + uniform() * (b - a);
	}
}
