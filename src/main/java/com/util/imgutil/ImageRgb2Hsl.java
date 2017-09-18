package com.util.imgutil;

import java.awt.Color;
import java.util.Arrays;

public class ImageRgb2Hsl {

	public static float[] rgb2hsb(int rgbR, int rgbG, int rgbB) {
		assert 0 <= rgbR && rgbR <= 255;
		assert 0 <= rgbG && rgbG <= 255;
		assert 0 <= rgbB && rgbB <= 255;
		int[] rgb = new int[] { rgbR, rgbG, rgbB };
		Arrays.sort(rgb);
		int max = rgb[2];
		int min = rgb[0];

		float hsbB = max / 255.0f;
		float hsbS = max == 0 ? 0 : (max - min) / (float) max;

		float hsbH = 0;
		if (max == rgbR && rgbG >= rgbB) {
			hsbH = (rgbG - rgbB) * 60f / (max - min) + 0;
		}
		else if (max == rgbR && rgbG < rgbB) {
			hsbH = (rgbG - rgbB) * 60f / (max - min) + 360;
		}
		else if (max == rgbG) {
			hsbH = (rgbB - rgbR) * 60f / (max - min) + 120;
		}
		else if (max == rgbB) {
			hsbH = (rgbR - rgbG) * 60f / (max - min) + 240;
		}

		return new float[] { hsbH, hsbS, hsbB };
	}

	public static int[] hsb2rgb(float h, float s, float v) {
		assert Float.compare(h, 0.0f) >= 0 && Float.compare(h, 360.0f) <= 0;
		assert Float.compare(s, 0.0f) >= 0 && Float.compare(s, 1.0f) <= 0;
		assert Float.compare(v, 0.0f) >= 0 && Float.compare(v, 1.0f) <= 0;

		float r = 0, g = 0, b = 0;
		int i = (int) ((h / 60) % 6);
		float f = (h / 60) - i;
		float p = v * (1 - s);
		float q = v * (1 - f * s);
		float t = v * (1 - (1 - f) * s);
		switch (i) {
		case 0:
			r = v;
			g = t;
			b = p;
			break;
		case 1:
			r = q;
			g = v;
			b = p;
			break;
		case 2:
			r = p;
			g = v;
			b = t;
			break;
		case 3:
			r = p;
			g = q;
			b = v;
			break;
		case 4:
			r = t;
			g = p;
			b = v;
			break;
		case 5:
			r = v;
			g = p;
			b = q;
			break;
		default:
			break;
		}
		return new int[] { (int) (r * 255.0), (int) (g * 255.0), (int) (b * 255.0) };
	}

	public static HSL RGB2HSL(RGB rgb) {
		if (rgb == null) {
			return null;
		}

		float H, S, L, var_Min, var_Max, del_Max, del_R, del_G, del_B;
		H = 0;
		var_Min = Math.min(rgb.red, Math.min(rgb.blue, rgb.green));
		var_Max = Math.max(rgb.red, Math.max(rgb.blue, rgb.green));
		del_Max = var_Max - var_Min;
		L = (var_Max + var_Min) / 2;
		if (del_Max == 0) {
			H = 0;
			S = 0;

		}
		else {
			if (L < 128) {
				S = 256 * del_Max / (var_Max + var_Min);
			}
			else {
				S = 256 * del_Max / (512 - var_Max - var_Min);
			}
			del_R = ((360 * (var_Max - rgb.red) / 6) + (360 * del_Max / 2)) / del_Max;
			del_G = ((360 * (var_Max - rgb.green) / 6) + (360 * del_Max / 2)) / del_Max;
			del_B = ((360 * (var_Max - rgb.blue) / 6) + (360 * del_Max / 2)) / del_Max;
			if (rgb.red == var_Max) {
				H = del_B - del_G;
			}
			else if (rgb.green == var_Max) {
				H = 120 + del_R - del_B;
			}
			else if (rgb.blue == var_Max) {
				H = 240 + del_G - del_R;
			}
			if (H < 0) {
				H += 360;
			}
			if (H >= 360) {
				H -= 360;
			}
			if (L >= 256) {
				L = 255;
			}
			if (S >= 256) {
				S = 255;
			}
		}
		return new HSL(H, S, L);
	}

	public static float[] rgb2HSV(int r, int g, int b)

	{

		float[] hsv = new float[3];

		Color.RGBtoHSB(r, g, b, hsv);

		return hsv;

	}

	public static double[] RGBtoHSV(double r, double g, double b) {

		double h, s, v;

		double min, max, delta;

		min = Math.min(Math.min(r, g), b);
		max = Math.max(Math.max(r, g), b);

		// V
		v = max;

		delta = max - min;

		// S
		if (max != 0)
			s = delta / max;
		else {
			s = 0;
			h = -1;
			return new double[] { h, s, v };
		}

		// H
		if (r == max)
			h = (g - b) / delta; // between yellow & magenta
		else if (g == max)
			h = 2 + (b - r) / delta; // between cyan & yellow
		else
			h = 4 + (r - g) / delta; // between magenta & cyan

		h *= 60; // degrees

		if (h < 0)
			h += 360;

		return new double[] { h, s, v };
	}
}
