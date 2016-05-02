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
package gplx.core.progs; import gplx.*; import gplx.core.*;
public interface Gfo_prog_ui {
	boolean			Prog__started();
	boolean			Prog__paused();
	boolean			Prog__finished();
	boolean			Prog__canceled();
	long			Prog__cur();
	long			Prog__end();
	void			Prog__start();
	void			Prog__pause();
	void			Prog__resume();
	void			Prog__cancel();
	byte			Prog__notify__working(long cur, long max);
	void			Prog__notify__finished();
}