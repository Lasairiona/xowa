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
package gplx.xowa.htmls.hrefs; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*;
import gplx.langs.htmls.encoders.*;
import gplx.xowa.wikis.xwikis.*;
public class Xoh_href_wtr {
	private final Bry_bfr encoder_bfr = Bry_bfr.reset_(255), tmp_bfr = Bry_bfr.reset_(255);
	private final Url_encoder encoder = Xoa_app_.Utl__encoder_mgr().Href(); 
	public byte[] Build_to_bry(Xow_wiki wiki, Xoa_ttl ttl) {
		synchronized (tmp_bfr) {
			Build_to_bfr(tmp_bfr, wiki.App(), wiki.Domain_bry(), ttl, Bool_.N);
			return tmp_bfr.To_bry_and_clear();
		}
	}
	public void Build_to_bfr(Bry_bfr bfr, Xoa_app app, byte[] domain_bry, Xoa_ttl ttl)	{Build_to_bfr(bfr, app, domain_bry, ttl, Bool_.N);}
	public void Build_to_bfr(Bry_bfr bfr, Xoa_app app, byte[] domain_bry, Xoa_ttl ttl, boolean force_site) {
		byte[] page = ttl.Full_txt_raw();
		Xow_xwiki_itm xwiki = ttl.Wik_itm();
		if (xwiki == null)																		// not an xwiki; EX: [[wikt:Word]]
			Build_to_bfr_page(ttl, page, 0);													// write page only; NOTE: changed to remove leaf logic DATE:2014-09-07
		else {																					// xwiki; skip wiki and encode page only;
			byte[] wik_txt = ttl.Wik_txt();
			Build_to_bfr_page(ttl, page, wik_txt.length + 1);
		}
		if (xwiki == null) {																	// not an xwiki
			if (ttl.Anch_bgn() != 1) {															// not an anchor-only;	EX: "#A"
				if (force_site) {																// popup parser always writes as "/site/"
					bfr.Add(Xoh_href_.Bry__site);												// add "/site/";	EX: /site/
					bfr.Add(domain_bry);														// add xwiki;		EX: en_dict	 
					bfr.Add(Xoh_href_.Bry__wiki);												// add "/wiki/";	EX: /wiki/
				}
				else
					bfr.Add(Xoh_href_.Bry__wiki);												// add "/wiki/";	EX: /wiki/Page
			}
			else {}																				// anchor: noop
		}
		else {																					// xwiki
			if (app.Xwiki_mgr__missing(xwiki.Domain_bry())) {									// xwiki is not offline; use http:
				Bry_fmtr url_fmtr = xwiki.Url_fmtr();
				if (url_fmtr == null) {
					bfr.Add(Xoh_href_.Bry__https);												// add "https://";	EX: https://
					bfr.Add(xwiki.Domain_bry());												// add xwiki;		EX: en_dict	 
					bfr.Add(Xoh_href_.Bry__wiki);												// add "/wiki/";	EX: /wiki/
				}
				else {																			// url_fmtr exists; DATE:2015-04-22
					url_fmtr.Bld_bfr(bfr, encoder_bfr.To_bry_and_clear());						// use it and pass encoder_bfr for page_name;
					return;
				}
			}
			else {																				// xwiki is avaiable; use /site/
				bfr.Add(Xoh_href_.Bry__site);													// add "/site/";	EX: /site/
				bfr.Add(xwiki.Domain_bry());													// add xwiki;		EX: en_dict	 
				bfr.Add(Xoh_href_.Bry__wiki);													// add "/wiki/";	EX: /wiki/
			}
		}
		bfr.Add_bfr_and_clear(encoder_bfr);
	}
	private void Build_to_bfr_page(Xoa_ttl ttl, byte[] ttl_full, int page_bgn) {
		int anch_bgn = Bry_find_.Find_fwd(ttl_full, Byte_ascii.Hash);	// NOTE: cannot use Anch_bgn b/c Anch_bgn has bug with whitespace
		if (anch_bgn == Bry_find_.Not_found)	// no anchor; just add page
			encoder.Encode(encoder_bfr, ttl_full, page_bgn, ttl_full.length);
		else {							// anchor exists; check if anchor is preceded by ws; EX: [[A #b]] -> "/wiki/A#b"
			int page_end = Bry_find_.Find_bwd_last_ws(ttl_full, anch_bgn);		// first 1st ws before #; handles multiple ws
			page_end = page_end == Bry_find_.Not_found ? anch_bgn : page_end;			// if ws not found, use # pos; else use 1st ws pos
			encoder.Encode(encoder_bfr, ttl_full, page_bgn, page_end);			// add page
			encoder.Encode(encoder_bfr, ttl_full, anch_bgn, ttl_full.length);	// add anchor
		}
	}
}
