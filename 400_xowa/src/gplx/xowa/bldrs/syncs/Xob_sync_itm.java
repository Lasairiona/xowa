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
package gplx.xowa.bldrs.syncs; import gplx.*; import gplx.xowa.*; import gplx.xowa.bldrs.*;
import gplx.core.ios.*;
class Xob_sync_grp {
	private final Ordered_hash itms = Ordered_hash_.New();
	public Xob_sync_grp Ctor_itm(DateAdp dump_time, DateAdp upload_time) {
		this.dump_time = dump_time; this.upload_time = upload_time;
		return this;
	}
	public DateAdp Dump_time() {return dump_time;} private DateAdp dump_time;
	public DateAdp Upload_time() {return upload_time;} private DateAdp upload_time;
	public int Itms__len() {return itms.Count();}
	public void Itms__add(Xob_sync_pkg file) {itms.Add(file.Path(), file);}
	public Xob_sync_pkg Itms__get_at(int i) {return (Xob_sync_pkg)itms.Get_at(i);}
}
class Xob_sync_pkg extends Xob_sync_fil {	private final Ordered_hash itms = Ordered_hash_.New();
	public Xob_sync_pkg Ctor_itm(String url, byte zip_tid) {
		this.url = url; this.zip_tid = zip_tid;
		return this;
	}
	public String Url() {return url;} private String url;
	public byte Zip_tid() {return zip_tid;} private byte zip_tid;
	public int Itms__len() {return itms.Count();}
	public void Itms__add(Xob_sync_fil file) {itms.Add(file.Path(), file);}
	public Xob_sync_fil Itms__get_at(int i) {return (Xob_sync_fil)itms.Get_at(i);}
}
class Xob_sync_fil {
	public Xob_sync_fil Ctor_file(String path, String name, int ext, long len, DateAdp modified, String hash) {
		this.path = path; this.name = name; this.ext = ext; this.len = len; this.modified = modified; this.hash = hash;
		return this;
	}
	public String Path() {return path;} private String path;
	public String Name() {return name;} private String name;
	public int Ext() {return ext;} private int ext;
	public long Len() {return len;} private long len;
	public DateAdp Modified() {return modified;} private DateAdp modified;
	public String Hash() {return hash;} private String hash;
}
