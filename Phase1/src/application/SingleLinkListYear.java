package application;

public class SingleLinkListYear {
	private NodeYear first, last;
	int count = 0;
	String date;
	public SingleLinkListYear(String date) {
		this.date=date;
	}
	public SingleLinkListYear() {
		this.date=date;
	}
	NodeYear getfirst() {
		return first;
	}

	NodeYear getLast() {
		return last;
	}
	public SingleLinkListMonth Search(SingleLinkListMonth element) {
		if (count == 0)
			return null;
		else if (first.equals(element))
			return this.first.getElement();
		else if (last.equals(element))
			return this.last.getElement();
		else {
			NodeYear current = first.getNext();
			while (current != null) {
				if (current.equals(element))
					return current.getElement();
				current = current.getNext();
			}
			return null;
		}
	}
	public boolean addSorted(SingleLinkListMonth element) {
		NodeYear newNode=new NodeYear(element);
		if(count==0	) {
			first=last=newNode;
			count++;
			return true;
		}else {
			NodeYear current=first;
			int count=0;
			for(int i=0;i<count;i++) {
				if(current.compareTo(element)>0) {
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
	void addFirst(SingleLinkListMonth element) {
		if (first == null) {
			first = last = new NodeYear(element);
		} else {
			NodeYear temp = new NodeYear(element);
			temp.setNext(first);
			first = temp;
		}
		count++;
	}

	void addLast(SingleLinkListMonth element) {
		if (count == 0) {
			first = last = new NodeYear(element);
		} else {
			NodeYear temp = new NodeYear(element);
			last.setNext(temp);
			last = temp;
			temp.next = first;
		}
		count++;
	}

	public void add(SingleLinkListMonth element, int index) {

		NodeYear newNode = new NodeYear(element);
		if (index <= 0) {
			addFirst(element);
		} else if (index > count) {
			addLast(element);
		} else {
			NodeYear current = first;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			NodeYear temp = current.next;
			NodeYear newnode = new NodeYear(element);
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
			NodeYear current = first;
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
			NodeYear current = first;
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
		NodeYear prev = first;
		if (count == 1) {
			return removeFirst();
		}
		if (index == count) {
			return removeLast();
		}
		if (index <= 0 || index > count) {
			return false;
		} else {
			NodeYear current = first.getNext();
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
			NodeYear current = first.getNext();
			for (int i = 1; i < count - 1; i++) {
				if (current.getElement().equals(element))
					return remove(i);
				current = current.getNext();
			}
			return false;
		}
	}

	public void printList() {
		if (count == 0) {
			return;
		}
		NodeYear current = first;
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
