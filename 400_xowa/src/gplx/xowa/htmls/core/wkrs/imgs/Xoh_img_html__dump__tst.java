/*
XOWA: the XOWA Offline Wiki Application
Copyright (C) 2012 gnosygnu@gmail.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package gplx.xowa.htmls.core.wkrs.imgs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.wkrs.*;
import org.junit.*; import gplx.xowa.htmls.core.makes.tests.*;
public class Xoh_img_html__dump__tst {
	private final Xoh_make_fxt fxt = new Xoh_make_fxt();
	public static final String 
	  Html__basic		= "<a href='/wiki/File:A.png' class='image' title='abc'><img alt='abc' data-xoimg='0|220|110|0.5|-1|-1' src='file:///mem/xowa/file/en.wikipedia.org/thumb/7/0/A.png/220px.png' width='220' height='110'/></a>"
	;
	@Before public void init() {fxt.Clear();}
	@Test   public void Basic()		{fxt.Test__html("[[File:A.png|220x110px|upright=.5|abc]]"			, Html__basic);}
}