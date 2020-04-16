/********************************
 *	프로젝트 : talk
 *	패키지   : com.kyj.kakao.talk
 *	작성일   : 2020. 4. 15.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package com.kyj.kakao.talk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class TalkTextApi {

	private File f;

	/**
	 * @param f
	 *            kakaotalk text file.
	 */
	public TalkTextApi(File f) {
		this.f = f;
	}

	private TalkContentHandler talkContentHandler = (date, time, userName, content) -> {
		System.out.printf(" <아이디> %s <시간> %s <대화> %s\n", userName, date + " " + time, content);
		return false;
	};

	/**
	 * @return the talkContentHandler
	 */
	public TalkContentHandler getTalkContentHandler() {
		return talkContentHandler;
	}

	/**
	 * @param talkContentHandler
	 *            the talkContentHandler to set
	 */
	public void setTalkContentHandler(TalkContentHandler talkContentHandler) {
		this.talkContentHandler = talkContentHandler;
	}

	public void parse() {
		try (var br = new TalkBufferedReader(new FileReader(this.f))) {
			String temp = null;

			String date = "";
			boolean stopReading;
			while ((temp = br.readLine()) != null) {

				int start = temp.indexOf('[');

				if (start == -1) {

					if (temp.startsWith("--------------- ")) {
						date = temp.replace("--------------- ", "");
						date = date.replace(" ---------------", "");
					}

					continue;
				}
				int end = temp.indexOf(']', start);

				String userName = temp.substring(start + 1, end);

				start = temp.indexOf('[', end);
				end = temp.indexOf(']', start);

				String time = temp.substring(start + 1, end);

				String talk = temp.substring(end + 1);

				stopReading = talkContentHandler.content(date, time, userName, talk);

				if (stopReading)
					break;

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
