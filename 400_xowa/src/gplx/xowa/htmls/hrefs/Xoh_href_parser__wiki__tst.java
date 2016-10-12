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
import org.junit.*; import gplx.xowa.apps.urls.*; import gplx.xowa.wikis.nss.*;
public class Xoh_href_parser__wiki__tst {
	private final    Xoh_href_parser_fxt fxt = new Xoh_href_parser_fxt();
	@Test   public void Basic() {
		fxt.Exec__parse_as_url("/wiki/A").Test__tid(Xoa_url_.Tid_page).Test__to_str("en.wikipedia.org/wiki/A").Test__wiki("en.wikipedia.org").Test__page("A");
	}
	@Test   public void Page__w_question() {
		fxt.Exec__parse_as_url("/wiki/%3F").Test__page("?");
	}
	@Test   public void Qarg() {
		fxt.Exec__parse_as_url("/wiki/A?action=edit").Test__page("A").Test__qargs("?action=edit").Test__to_str("en.wikipedia.org/wiki/A?action=edit");
	}
	@Test   public void Qarg__w_question() {
		fxt.Exec__parse_as_url("/wiki/A%3F?action=edit").Test__page("A?").Test__qargs("?action=edit");
	}
	@Test   public void Anchor() {
		fxt.Exec__parse_as_url("/wiki/A#b").Test__to_str("en.wikipedia.org/wiki/A#b").Test__anch("b");
	}
	@Test   public void Xwiki__only()	{
		fxt.Prep_add_xwiki_to_wiki("c", "commons.wikimedia.org");
		fxt.Exec__parse_as_url("/wiki/c:").Test__page_is_main_y().Test__page("Main_Page").Test__to_str("commons.wikimedia.org/wiki/Main_Page");
	}
	@Test   public void Encoded() {
		fxt.Exec__parse_as_url("/wiki/A%22b%22c").Test__page("A\"b\"c");
	}
	@Test   public void Triple_slash() {	// PURPOSE: handle triple slashes; PAGE:esolangs.org/wiki/Language_list; DATE:2015-11-14
		fxt.Exec__parse_as_url("/wiki////").Test__to_str("en.wikipedia.org/wiki////").Test__wiki("en.wikipedia.org").Test__page("///");
	}
	@Test   public void Http() {	// PURPOSE: variant of triple slashes; DATE:2015-11-14
		fxt.Exec__parse_as_url("/wiki/http://a").Test__to_str("en.wikipedia.org/wiki/Http://a").Test__wiki("en.wikipedia.org").Test__page("Http://a");
	}
}
