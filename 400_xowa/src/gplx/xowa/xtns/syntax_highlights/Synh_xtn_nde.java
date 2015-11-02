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
package gplx.xowa.xtns.syntax_highlights; import gplx.*; import gplx.xowa.*; import gplx.xowa.xtns.*;
import gplx.core.primitives.*;
import gplx.xowa.parsers.*; import gplx.xowa.parsers.xndes.*; import gplx.xowa.parsers.htmls.*;
import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.htmls.*;
public class Synh_xtn_nde implements Xox_xnde, Mwh_atr_itm_owner {
	private byte[] lang = Bry_.Empty; private byte[] style = null; private byte[] enclose = Bry_.Empty;
	private boolean line_enabled = false; private int start = 1; private Int_rng_mgr highlight_idxs = Int_rng_mgr_null.Instance;
	public Xop_xnde_tkn Xnde() {throw Err_.new_unimplemented();}
	public void Xatr__set(Xowe_wiki wiki, byte[] src, Mwh_atr_itm xatr, Object xatr_id_obj) {
		if (xatr_id_obj == null) return;
		Byte_obj_val xatr_id = (Byte_obj_val)xatr_id_obj;
		switch (xatr_id.Val()) {
			case Xatr_line:			line_enabled	= true; break;
			case Xatr_enclose:		enclose			= xatr.Val_as_bry(); break;
			case Xatr_lang:			lang			= xatr.Val_as_bry(); break;
			case Xatr_style:		style			= xatr.Val_as_bry(); break;
			case Xatr_start:		start			= xatr.Val_as_int_or(1); break;
			case Xatr_highlight:	highlight_idxs	= new Int_rng_mgr_base(); highlight_idxs.Parse(xatr.Val_as_bry()); break;
		}
	}
	public void Xtn_parse(Xowe_wiki wiki, Xop_ctx ctx, Xop_root_tkn root, byte[] src, Xop_xnde_tkn xnde) {
		Xop_xnde_tag tag = xnde.Tag();
		ctx.Para().Process_block__xnde(tag, tag.Block_open());	// deactivate pre; pre; PAGE:en.w:Comment_(computer_programming); DATE:2014-06-24
		Xox_xnde_.Xatr__set(wiki, this, xatrs_hash, src, xnde);
		ctx.Para().Process_block__xnde(tag, tag.Block_close());	// deactivate pre; pre; PAGE:en.w:Comment_(computer_programming); DATE:2014-06-24
	}
	public void Xtn_write(Bry_bfr bfr, Xoae_app app, Xop_ctx ctx, Xoh_html_wtr html_wtr, Xoh_wtr_ctx hctx, Xop_xnde_tkn xnde, byte[] src) {
		Synh_xtn_nde_.Make(bfr, app, src, xnde.Tag_open_end(), xnde.Tag_close_bgn(), lang, enclose, style, line_enabled, start, highlight_idxs);
	}
	private static final byte Xatr_enclose = 1, Xatr_lang = 2, Xatr_style = 3, Xatr_line = 4, Xatr_start = 5, Xatr_highlight = 6;
	private static final Hash_adp_bry xatrs_hash = Hash_adp_bry.ci_a7()
	.Add_str_byte("enclose"		, Xatr_enclose)
	.Add_str_byte("inline"		, Xatr_enclose)
	.Add_str_byte("lang"		, Xatr_lang)
	.Add_str_byte("style"		, Xatr_style)
	.Add_str_byte("line"		, Xatr_line)
	.Add_str_byte("start"		, Xatr_start)
	.Add_str_byte("highlight"	, Xatr_highlight)
	;
}
