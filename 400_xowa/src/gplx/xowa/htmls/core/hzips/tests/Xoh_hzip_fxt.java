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
package gplx.xowa.htmls.core.hzips.tests; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*; import gplx.xowa.htmls.core.hzips.*;
import gplx.xowa.htmls.core.hzips.stats.*;
public class Xoh_hzip_fxt {
	private final Bry_bfr bfr = Bry_bfr.new_();
	private final Xoh_hzip_mgr hzip_mgr;
	private final Hzip_stat_itm stat_itm = new Hzip_stat_itm();
	public Xoh_hzip_fxt() {
		Xoa_app_fxt.repo2_(parser_fxt.App(), parser_fxt.Wiki());	// needed else will be old "mem/wiki/repo/trg/thumb/" instead of standard "mem/file/en.wikipedia.org/thumb/"
		parser_fxt.Wiki().Html__hdump_mgr().Init_by_db(parser_fxt.Wiki());
		this.hzip_mgr = parser_fxt.Wiki().Html__hdump_mgr().Hzip_mgr().Hzip_mgr();
	}
	public Xop_fxt Parser_fxt() {return parser_fxt;} private final Xop_fxt parser_fxt = new Xop_fxt();
	public Xoh_hzip_mgr Hzip_mgr() {return hzip_mgr;}
	public void Test__bicode(String hzip, String html) {
		hzip = String_.Replace(Xoh_hzip_fxt.Escape(hzip), "'", "\""); html = String_.Replace(html, "'", "\"");
		Test__encode__raw(hzip, html);
		Test__decode__raw(hzip, html);
	}
	public void Test__encode(String hzip, String html) {
		hzip = String_.Replace(Xoh_hzip_fxt.Escape(hzip), "'", "\""); html = String_.Replace(html, "'", "\"");
		Test__encode__raw(hzip, html);
	}
	public void Test__decode(String hzip, String html) {
		hzip = String_.Replace(Xoh_hzip_fxt.Escape(hzip), "'", "\""); html = String_.Replace(html, "'", "\"");
		Test__decode__raw(hzip, html);
	}
	public void Test__encode__fail(String expd, String html) {
		hzip_mgr.Encode(bfr, parser_fxt.Wiki(), Bry_.Empty, Bry_.new_u8(html), stat_itm);
		Tfds.Eq_str_lines(expd, bfr.To_str_and_clear());
	}
	private void Test__encode__raw(String hzip, String html) {
		Gfo_usr_dlg_.Test__show__init();
		hzip_mgr.Encode(bfr, parser_fxt.Wiki(), Bry_.Empty, Bry_.new_u8(html), stat_itm);
		Gfo_usr_dlg_.Test__show__term();
		Tfds.Eq_str_lines(hzip, bfr.To_str_and_clear());
	}
	private void Test__decode__raw(String hzip, String html) {
		Gfo_usr_dlg_.Test__show__init();
		byte[] actl = hzip_mgr.Decode(bfr, parser_fxt.Wiki(), Bry_.Empty, Bry_.new_u8(hzip));
		Gfo_usr_dlg_.Test__show__term();
		Tfds.Eq_str_lines(html, String_.new_u8(actl));
	}
	public static String Escape(String v) {return String_.Replace(v, "~", "");}
}