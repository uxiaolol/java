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
	 * 随机返回0到1之间的实数 [0,1)
	 * 
	 * @return double 随机数
	 */
	public static double uniform() {
		Random random = new SecureRandom();
		return random.nextDouble();
	}

	/**
	 * 随机返回0到N-1之间的整数 [0,N)
	 * 
	 * @param N
	 *            上限
	 * @return int 随机数
	 */
	public static int uniform(int N) {
		Random random = new SecureRandom();
		return random.nextInt(N);
	}

	/**
	 * 随机返回0到1之间的实数 [0,1)
	 * 
	 * @return double 随机数
	 */
	public static double random() {
		return uniform();
	}

	/**
	 * 随机返回a到b-1之间的整数 [a,b)
	 * 
	 * @param a
	 *            下限
	 * @param b
	 *            上限
	 * @return int 随机数
	 */
	public static int uniform(int a, int b) {
		return a + uniform(b - a);
	}

	/**
	 * 随机返回a到b之间的实数
	 * 
	 * @param a
	 *            下限
	 * @param b
	 *            上限
	 * @return double 随机数
	 */
	public static double uniform(double a, double b) {
		return a + uniform() * (b - a);
	}
}
