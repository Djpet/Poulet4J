package fr.poulet4j.save;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public final class StringCompressor {
	public static String compress(final String text) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			OutputStream out = new DeflaterOutputStream(baos, new Deflater(Deflater.BEST_SPEED), 5000);
			out.write(text.getBytes("UTF-8"));
			out.close();
		} catch (IOException e) {
			throw new AssertionError(e);
		}
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}

	public static String decompress(final String src) {
		InputStream in = new InflaterInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(src)));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		try {
			int len;
			while ((len = in.read(buffer)) > 0) {
				baos.write(buffer, 0, len);
			}
			return new String(baos.toByteArray(), "UTF-8");
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}
}
