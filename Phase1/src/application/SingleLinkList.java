package application;

public class SingleLinkList {
	private Node first, last;
	int count = 0;
	String date;
	public SingleLinkList(String date) {
		this.date=date;
	}
	Node getfirst() {
		return first;
	}

	Node getLast() {
		return last;
	}
	public boolean addSorted(Object element) {
		Node newNode=new Node(element);
		if(count==0	) {
			first=last=newNode;
			count++;
			return true;
		}else {
			Node current=first;
			int count=0;
			for(int i=0;i<count;i++) {
				if(current.compareTo((Record)element)>0) {
					this.add(element,count);
					return true;
				}
				count++;
				current=current.getNext();
			}
			this.addLast(element);
			return true;
		}
	}
	void addFirst(Object element) {
		if (first == null) {
			first = last = new Node(element);
		} else {
			Node temp = new Node(element);
			temp.setNext(first);
			first = temp;
		}
		count++;
	}

	void addLast(Object element) {
		if (count == 0) {
			first = last = new Node(element);
		} else {
			Node temp = new Node(element);
			last.setNext(temp);
			last = temp;
			temp.next = first;
		}
		count++;
	}

	public void add(Object element, int index) {

		Node newNode = new Node(element);
		if (index <= 0) {
			addFirst(element);
		} else if (index > count) {
			addLast(element);
		} else {
			Node current = first;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			Node temp = current.next;
			Node newnode = new Node(element);
			current.next = newNode;
			newNode.next = temp;
			count++;

		}
	}

	public boolean removeFirst() {
		if (count == 0) {
			return false;
		}
		if (count == 1) {
			first = last = null;
		} else {
			Node current = first;
			first = first.getNext();
			current = null;
		}
		count--;
		return true;
	}

	public boolean removeLast() {
		if (count == 0) {
			return false;
		}
		if (count == 1) {
			first = last = null;
		} else {
			Node current = first;
			for (int i = 0; i < count; i++) {
				current = current.getNext();
			}
			last = current;
			last.setNext(null);
			last.next = first;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		Node prev = first;
		if (count == 1) {
			return removeFirst();
		}
		if (index == count) {
			return removeLast();
		}
		if (index <= 0 || index > count) {
			return false;
		} else {
			Node current = first.getNext();
			for (int i = 1; i < index; i++) {
				prev = current;
				current = current.getNext();
			}
			prev.setNext(current.getNext());
			current.setNext(null);
			count--;
			return true;
		}
	}

	public boolean remove(Object element) {
		if (count == 0)
			return false;
		if (element.equals(first.getElement()))
			return removeFirst();
		if (element.equals(last.getElement()))
			return removeLast();
		else {
			Node current = first.getNext();
			for (int i = 1; i < count - 1; i++) {
				if (current.getElement().equals(element))
					return remove(i);
				current = current.getNext();
			}
			return false;
		}
	}
	public Object Search(Object element) {
		if (count == 0)
			return null;
		else if (first.equals(element))
			return this.first.getElement();
		else if (last.equals(element))
			return this.last.getElement();
		else {
			Node current = first.getNext();
			while (current != null) {
				if (current.equals(element))
					return current.getElement();
				current = current.getNext();
			}
			return null;
		}
	}
	public void printList() {
		if (count == 0) {
			return;
		}
		Node current = first;
		for (int i = 0; i < count; i++) {
			System.out.println(current.getElement().toString());
			current = current.getNext();
		}
	}
	@Override
	public String toString() {
		return "SingleLinkList [date=" + date + "]";
	}
}
