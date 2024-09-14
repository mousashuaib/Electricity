package application;

public class Node {
	Node next;
	private Object element;

	Node(Object element) {
		this.element = element;
	}

	public Node getNext() {
		return next;
	}

	public int compareTo(Record obj) {
		return ((Record) this.element).getDay().compareTo(obj.getDay());
	}

	public boolean equals(Record obj) {
		return ((Record) this.element).getDay().equals(obj.getDay());
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public boolean equals(Object obj) {
		if (element.equals(obj))
			return true;
		return false;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return "Node [next=" + next + ", element=" + element + "]";
	}
}
