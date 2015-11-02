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
package gplx.xowa.htmls.core.hzips; import gplx.*; import gplx.xowa.*; import gplx.xowa.htmls.*; import gplx.xowa.htmls.core.*;
import gplx.core.primitives.*; import gplx.core.btries.*;
import gplx.xowa.htmls.core.wkrs.spaces.*; import gplx.xowa.htmls.core.wkrs.hdrs.*; import gplx.xowa.htmls.core.wkrs.lnkes.*; import gplx.xowa.htmls.core.wkrs.lnkis.*; import gplx.xowa.htmls.core.wkrs.escapes.*;
public class Xoh_hzip_dict_ {
	public static final byte Escape = Byte_.By_int(27);			// SERIALIZED: 27=escape byte
	public static final byte[] Escape_bry = Bry_.new_ints(27);	// SERIALIZED
	private static final byte Base85_ascii = 33;
	public static final byte			// SERIALIZED
	  Tid__space				=   0 + Base85_ascii
	, Tid__hdr					=   1 + Base85_ascii
	, Tid__lnke					=   2 + Base85_ascii
	, Tid__lnki					=   3 + Base85_ascii
	, Tid__escape				=  84 + Base85_ascii
	;
	public static final byte[]
	  Bry__space				= Bry_.new_ints(Escape, Tid__space)
	, Bry__hdr					= Bry_.new_ints(Escape, Tid__hdr)
	, Bry__lnke					= Bry_.new_ints(Escape, Tid__lnke)
	, Bry__lnki					= Bry_.new_ints(Escape, Tid__lnki)
	, Bry__escape				= Bry_.new_ints(Escape, Tid__escape)
	;
	public static final String
	  Key__space				= "space"
	, Key__hdr					= "hdr"
	, Key__lnke					= "lnke"
	, Key__lnki					= "lnki"
	, Key__escape				= "escape"
	;
	public static Xoh_hzip_wkr To_wkr(byte tid) {
		switch (tid) {
			case Tid__space:		return Wkr__space;
			case Tid__hdr:			return Wkr__hdr;
			case Tid__lnke:			return Wkr__lnke;
			case Tid__lnki:			return Wkr__lnki;
			case Tid__escape:		return Wkr__escape;
			default:				throw Err_.new_unhandled(tid);
		}
	}
	public static final Btrie_slim_mgr Trie = Btrie_slim_mgr.cs()
	.Add_bry_byte(Xoh_html_dict_.Hook__space	, Xoh_hzip_dict_.Tid__space)	// 4 spaces b/c escape code will be 3 bytes
	.Add_bry_byte(Xoh_html_dict_.Type__hdr		, Xoh_hzip_dict_.Tid__hdr)
	.Add_bry_byte(Xoh_html_dict_.Hook__lnke		, Xoh_hzip_dict_.Tid__lnke)
	.Add_bry_byte(Xoh_html_dict_.Hook__lnki		, Xoh_hzip_dict_.Tid__lnki)
	.Add_bry_byte(Escape_bry					, Xoh_hzip_dict_.Tid__escape)
	;
	public static final Xoh_hzip_wkr
	  Wkr__space		= new Xoh_space_hzip()
	, Wkr__hdr			= new Xoh_hdr_hzip()
	, Wkr__lnke			= new Xoh_lnke_hzip()
	, Wkr__lnki			= new Xoh_lnki_hzip()
	, Wkr__escape		= new Xoh_escape_hzip()
	;
}
