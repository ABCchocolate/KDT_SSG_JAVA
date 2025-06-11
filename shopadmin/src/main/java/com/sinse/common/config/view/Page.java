package com.sinse.common.config.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.sinse.shopadmin.AppMain;

import common.config.Config;

public class Page  extends JPanel{
	// 모든 페이지는 appMain안에 소속 페이지들이므로, 서로 공유할 ㅔ이터가ㅏ 있다면 APPMAIN을 통하여 공유하도록한다.
	protected AppMain appMain;
	public Page(AppMain appMain) {
		this.appMain = appMain;
	    setPreferredSize(new Dimension(
	        Config.ADMINMAIN_WIDTH - Config.SIDE_WIDTH,
	        Config.ADMINMAIN_HEIGHT - Config.UTIL_HEIGHT
	    ));
	    setVisible(false);
	}

}