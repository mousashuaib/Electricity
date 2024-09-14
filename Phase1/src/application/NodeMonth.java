package application;

public class NodeMonth {
	NodeMonth next;
	private SingleLinkList element;

	NodeMonth(SingleLinkList element) {
		this.element = element;
	}

	public int compareTo(SingleLinkList obj) {
		return this.element.date.compareTo(obj.date);
	}

	public boolean equals(SingleLinkList obj) {
		return this.element.date.equals(obj.date);
	}

	public NodeMonth getNext() {
		return next;
	}

	public void setNext(NodeMonth next) {
		this.next = next;
	}

	@Override
	public boolean equals(Object obj) {
		if (element.equals(obj))
			return true;
		return false;
	}

	public SingleLinkList getElement() {
		return element;
	}

	public void setElement(SingleLinkList element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return "Node [next=" + next + ", element=" + element + "]";
	}
}
