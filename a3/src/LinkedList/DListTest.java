package LinkedList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DListTest {

	@Test
	void testappend() {
		DList<String> d=new DList<>();
		assertEquals(0,d.size());
		d.append("abc");
		assertEquals(1,d.size());
		assertEquals(d.head(),d.tail());
		assertEquals(null, d.head().pred());
		assertEquals(null, d.head().succ());
		d.append("123");
		assertEquals(2,d.size());
		assertEquals(d.tail(), d.head().succ());
		assertEquals(null, d.tail().succ());
		assertEquals(d.head(), d.tail().pred());
		assertEquals("[abc, 123]", d.toString());
		d.append("");
		assertEquals("[abc, 123, ]",d.toString());
		assertEquals(null, d.head().pred());
		assertEquals(d.head().succ(),d.tail().pred());
		assertEquals(d.tail(), d.head().succ().succ());
		assertEquals(d.head(),d.tail().pred().pred());
		assertEquals(null, d.tail().succ());
		d.append(null);//making sure when value in node is null everything works fine
		assertEquals(null, d.tail().succ());
		assertEquals("[abc, 123, , null]",d.toString());
	}
	
	@Test
	void testtoStringRev() {
		DList<String> d=new DList<>();
		assertEquals("[]",d.toStringRev());
		d.append(""); 
		assertEquals("[]",d.toStringRev());
		d.append("");
		assertEquals("[, ]", d.toStringRev());
		d.append("abc");
		d.append("123");
		assertEquals("[123, abc, , ]", d.toStringRev());
		DList<Integer> i=new DList<>();
		i.append(4);i.append(7);i.append(8);
		assertEquals("[8, 7, 4]",i.toStringRev());
	}
	
	@Test
	void testprepend() {
		DList<String> d=new DList<>();
		d.prepend("abc");
		assertEquals(1,d.size());
		d.prepend("123");
		assertEquals(2,d.size());
		assertEquals("[abc, 123]", d.toStringRev());
		d.prepend("");
		assertEquals("[abc, 123, ]",d.toStringRev());
		assertEquals(d.head().pred(),null);
		assertEquals(d.head().succ(),d.tail().pred());
		assertEquals(d.head().succ().succ(),d.tail());
		assertEquals(d.head(),d.tail().pred().pred());
		assertEquals(d.tail().succ(),null);
		d.prepend(null);//making sure when value in node is null everything works fine
		assertEquals(d.head().pred(),null);
		assertEquals("[abc, 123, , null]",d.toStringRev());
	}
	
	@Test
	void testgetNode() {
		DList<Integer> d=new DList<>();
		d.append(0);d.append(1);d.append(2);d.append(3); //Setting up list
		d.append(4);d.append(5);d.append(6);
		assertEquals(new Integer(0), d.getNode(0).value());//making sure each one works
		assertEquals(new Integer(1), d.getNode(1).value());
		assertEquals(new Integer(2), d.getNode(2).value());
		assertEquals(new Integer(3), d.getNode(3).value());
		assertEquals(new Integer(4), d.getNode(4).value());
		assertEquals(new Integer(5), d.getNode(5).value());
		assertEquals(new Integer(6), d.getNode(6).value());
		assertEquals(d.head(), d.getNode(0));//making sure head is set properly
		assertEquals(d.tail(), d.getNode(d.size()-1));//same for tail
		d.prepend(-1);//Still works if elements are added to beginning
		assertEquals(new Integer(-1), d.getNode(0).value());
		assertEquals(new Integer(6), d.getNode(7).value());
		assertEquals(d.head(), d.getNode(0));//making sure head updates properly
		assertEquals(d.tail(), d.getNode(d.size()-1));//same for tail
		d.append(7);//still works when elements added to end
		assertEquals(new Integer(-1),d.getNode(0).value());
		assertEquals(new Integer(6), d.getNode(7).value());
		assertEquals(new Integer(7), d.getNode(8).value());
		assertEquals(d.head(), d.getNode(0));//see above
		assertEquals(d.tail(), d.getNode(d.size()-1));
		assertThrows(AssertionError.class,()->{d.getNode(-1);});
		assertThrows(AssertionError.class,()->{d.getNode(20);});
	}
	
	@Test
	void testdelete() {
		DList<Integer> d=new DList<>();
		d.append(0);d.append(1);d.append(2);d.append(3);//set up
		d.append(4);d.append(5);d.append(6);
		d.delete(d.getNode(3));
		assertEquals("[0, 1, 2, 4, 5, 6]", d.toString());//makes sure the list is what I expect
		assertEquals(6,d.size());//Size updates properly
		assertEquals(d.head().succ().succ(),d.tail().pred().pred().pred());//elements around
		assertEquals(d.head().succ().succ().succ(),d.tail().pred().pred());//update right
		d.delete(d.head());//works on first element
		assertEquals("[1, 2, 4, 5, 6]", d.toString());
		assertEquals(d.head(),d.getNode(1).pred());
		assertEquals(d.head().pred(),null);//new head has right properties
		assertEquals(d.head().succ(),d.getNode(1));
		assertEquals(5,d.size());//size updates
		d.delete(d.tail());//works right with last element
		assertEquals("[1, 2, 4, 5]", d.toString());
		assertEquals(d.tail(),d.getNode(3));
		assertEquals(d.tail(),d.getNode(2).succ());
		assertEquals(4,d.size());
		d.delete(d.getNode(2));
		assertEquals("[1, 2, 5]", d.toString());
		assertEquals(d.getNode(1).succ(),d.getNode(2));
		assertEquals(d.tail(),d.getNode(2));
		assertEquals(3,d.size());
		assertThrows(AssertionError.class,()->{d.insertAfter(8, null);});
	
	}
	
	@Test
	void testinsertAfter() {
		DList<String> d=new DList<>();
		d.append("abc");d.append("123");d.append("");d.append("tail");
		d.insertAfter("head", d.tail());
		assertEquals("[abc, 123, , tail, head]",d.toString());
		assertEquals(5,d.size());
		assertEquals(d.tail(),d.getNode(3).succ());
		assertEquals(d.tail(), d.getNode(4));
		assertEquals("head",d.tail().value());
		d.insertAfter("aaaa", d.head());
		assertEquals("[abc, aaaa, 123, , tail, head]",d.toString());
		assertEquals(6,d.size());
		d.insertAfter("ababa", d.getNode(2));
		assertEquals("[abc, aaaa, 123, ababa, , tail, head]",d.toString());
		assertEquals(7,d.size());
		d.insertAfter("", d.getNode(4));
		assertEquals("[abc, aaaa, 123, ababa, , , tail, head]",d.toString());
		assertEquals(8,d.size());
		assertThrows(AssertionError.class,()->{d.insertAfter("12", null);});
	}
}
