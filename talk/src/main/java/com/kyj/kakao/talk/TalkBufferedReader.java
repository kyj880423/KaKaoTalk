/********************************
 *	프로젝트 : talk
 *	패키지   : com.kyj.kakao.talk
 *	작성일   : 2020. 4. 15.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.kakao.talk;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class TalkBufferedReader implements Closeable {

	private BufferedReader br;
	private List<String> lines;
	private int pos;

	public TalkBufferedReader(Reader br) {
		this.br = new BufferedReader(br);
		lines = this.br.lines().collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.BufferedReader#readLine()
	 */
	public String readLine() throws IOException {
		int size = lines.size();
		if (lines.size() <= pos)
			return null;

		String string = lines.get(pos++);

		StringBuffer sb = new StringBuffer(string);
		while (pos < size) {
			String next = lines.get(pos);

			if (next.length() != 0 && next.charAt(0) == '[') {
				break;
			}

			sb.append(next).append("\n");
			pos++;
		}

		return sb.toString();
	}

	@Override
	public void close() throws IOException {

	}

}
