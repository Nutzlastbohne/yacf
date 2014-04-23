package org.waagh.yacf.app.model;

import java.util.*;

/**
 * Created by Moe on 23.04.2014.
 */
public class NoteLane<E> implements NavigableSet<E> {
	private transient List<E> list;

	public NoteLane() {
		list = new LinkedList<>();
	}

	public NoteLane(List<E> list) {
		this();
		this.list = list;
	}

	@Override public E lower(E o) {
		int index = list.indexOf(o);
		return index > 1 ? list.get(index - 1) : null;
	}

	@Override public E floor(E o) {
		int index = list.indexOf(o);
		return index > 0 ? list.get(index - 1) : null;
	}

	@Override public E ceiling(E o) {
		return null;
	}

	@Override public E higher(E o) {
		return null;
	}

	@Override public E pollFirst() {
		return null;
	}

	@Override public E pollLast() {
		return null;
	}

	@Override public Iterator iterator() {
		return list.iterator();
	}

	@Override public NavigableSet descendingSet() {
		List revertedList = new LinkedList<>(list);
		Collections.reverse(revertedList);

		return new NoteLane<>(revertedList);
	}

	@Override public Iterator descendingIterator() {
		return descendingSet().iterator();
	}

	@Override public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
		return null;
	}

	@Override public NavigableSet<E> headSet(E toElement, boolean inclusive) {
		return null;
	}

	@Override public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
		return null;
	}

	@Override public SortedSet<E> subSet(E fromElement, E toElement) {
		return null;
	}

	@Override public SortedSet<E> headSet(E toElement) {
		return null;
	}

	@Override public SortedSet<E> tailSet(E fromElement) {
		return null;
	}

	@Override public int size() {
		return list.size();
	}

	@Override public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override public Object[] toArray(Object[] a) {
		return list.toArray(a);
	}

	@Override public Object[] toArray() {
		return list.toArray();
	}

	@Override public boolean add(E o) {
		//TODO: check if present
		//TODO: sort
		//TODO: Link?

		return list.add(o);
	}

	@Override public boolean remove(Object o) {
		return false;
	}

	@Override public boolean containsAll(Collection c) {
		return false;
	}

	@Override public boolean addAll(Collection c) {
		return false;
	}

	@Override public boolean retainAll(Collection c) {
		return false;
	}

	@Override public boolean removeAll(Collection c) {
		return false;
	}

	@Override public void clear() {

	}

	@Override public Comparator comparator() {
		return null;
	}

	@Override public E first() {
		return null;
	}

	@Override public E last() {
		return null;
	}

	@Override public Spliterator spliterator() {
		return null;
	}
}
