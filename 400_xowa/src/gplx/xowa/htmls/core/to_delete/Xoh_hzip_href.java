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
//namespace gplx.xowa.htmls.core.hzips.wkrs {
//	using gplx.core.btries;
//	using gplx.xowa.htmls.core.hzips.stats; 
//	public class Xoh_hzip_href {
//		public void Save(Bry_bfr bfr, Hzip_stat_itm stats, byte[] src, int src_len, int bgn, int pos, byte bgn_quote) {
////			// ignore anchors; EX: "#a"
////			int proto_bgn	= pos;
////			int proto_end	= Bry_find_.Find_fwd(src, Byte_ascii.Colon, proto_bgn, src_len);
////			byte proto_tid	= Tid_proto_other;
////			if (proto_end != Bry_find_.Not_found) {
////				Object proto_obj = proto_trie.Match_exact(src, pos, proto_bgn);
////				if (proto_obj != null)
////					proto_tid = ((Byte_obj_val)proto_obj).Val();
////				pos = Bry_find_.Find_fwd_while(src, proto_bgn + 1, src_len, Byte_ascii.Slash);	// eat /; EX: http:// should position after /
////			}
////			// stats.Lnke_proto_reg(proto_tid, src, proto_bgn, proto_end);
////
////			int domain_bgn		= pos;
////			int domain_end		= Bry_find_.Find_fwd(src, Byte_ascii.Slash, domain_bgn, src_len);
////			if (domain_end == Bry_find_.Not_found)		// href has no slash; assume entire String is domain EX: "www.a.org"
////				domain_end = Bry_find_.Find_fwd(src, bgn_quote, pos, src_len);
////
////			int tld_pos = Bry_find_.Find_bwd(src, Byte_ascii.Dot, domain_bgn, src_len);
////			byte tld_tid = Tid_tld_other;
////			if (tld_pos != Bry_find_.Not_found) {
////				Object tld_obj = tld_trie.Match_exact(src, domain_bgn, domain_end);
////				if (tld_obj != null)
////					tld_tid = ((Byte_obj_val)tld_obj).Val();
////				pos = Bry_find_.Find_fwd_while(src, domain_bgn + 1, src_len, Byte_ascii.Slash);	// eat /; EX: http:// should position after /
////			}
////			// stats.Lnke_tld_reg(tld_tid, src, domain_bgn, domain_end);
//		}
//		public static final byte	// 2
//		  Tid_proto_other	= 0
//		, Tid_proto_http	= 1
//		, Tid_proto_https	= 2
//		;
//		public static final byte	// 3
//		  Tid_tld_other		= 0
//		, Tid_tld_com		= 1
//		, Tid_tld_org		= 2
//		, Tid_tld_net		= 3
//		, Tid_tld_gov		= 4
//		;
//		public static final byte	// 3
//		  Tid_ext_other		= 0
//		, Tid_ext_none		= 1
//		, Tid_ext_htm		= 2
//		, Tid_ext_html		= 3
//		, Tid_ext_php		= 4
//		, Tid_ext_jsp		= 5
//		, Tid_ext_asp		= 6
//		, Tid_ext_aspx		= 7
//		;
////		private static final Btrie_slim_mgr proto_trie = Btrie_slim_mgr.ci_a7()
////		.Add_str_byte("http", Tid_proto_http)
////		.Add_str_byte("https", Tid_proto_http)
////		;
////		private static final Btrie_slim_mgr tld_trie = Btrie_slim_mgr.ci_a7()
////		.Add_str_byte("com", Tid_tld_com)
////		.Add_str_byte("org", Tid_tld_org)
////		.Add_str_byte("net", Tid_tld_net)
////		.Add_str_byte("gov", Tid_tld_gov)
////		;
////		private static final Btrie_slim_mgr ext_trie = Btrie_slim_mgr.ci_a7()
////		.Add_str_byte("htm", Tid_ext_htm)
////		.Add_str_byte("html", Tid_ext_html)
////		.Add_str_byte("php", Tid_ext_php)
////		.Add_str_byte("jsp", Tid_ext_jsp)
////		.Add_str_byte("asp", Tid_ext_asp)
////		.Add_str_byte("aspx", Tid_ext_aspx)
////		;		
//		// <a href="/site/simple.wikipedia.org/wiki/Template:Solar_System?action=edit"><span title="Edit this template" style="">e</span></a>	// xwiki [[simple:xx
//		// <a href="http://planetarynames.wr.usgs.gov/jsp/append5.jsp" class="external text" rel="nofollow">"Descriptor Terms (Feature Types)"</a>
//		/*
//		0: proto,tld,ext
//		1-n: domain
//		n: 0: domain_end
//		n: url remainder
//		n: 0: url_end
//		*/
//	}
//}
//namespace gplx.xowa.htmls.core.hzips.wkrs {
//	import org.junit.*; using gplx.xowa.htmls;
//	public class Xoh_hzip_href_tst {
//		@Before public void init() {fxt.Clear();} private Xoh_hzip_mgr_fxt fxt = new Xoh_hzip_mgr_fxt();
//		@Test   public void Srl_lnki_img_full() {
//			byte[][] brys = Bry_.Ary(Xoh_hzip_dict_.Bry__img_full, Bry_.new_ints(7), fxt.Make_int(12), Bry_.new_a7("cls_other"), Bry_.new_a7("|caption_other"), Xoh_hzip_dict_.Escape_bry);
//			fxt.Test_save(brys, "<a xtid='a_img_full' xatrs='1|1|1|12|cls_other|caption_other'/>");
////			fxt.Test_load(brys, "a_1<a href='/wiki/A' title='A'>A</a>a_2");
//		}
//	}
//}
