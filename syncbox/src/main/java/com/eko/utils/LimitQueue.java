package com.eko.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LimitQueue<T> implements Queue<T> {
	private int limit;
	
	Queue<T> queue = new LinkedList<T>();
	
	public LimitQueue(int limit){
		this.limit = limit;
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return queue.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return queue.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return queue.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return queue.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return queue.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return queue.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return queue.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return queue.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return queue.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		queue.clear();
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		return queue.add(e);
	}

	@Override
	public boolean offer(T e) {
		if(queue.size()>=limit){
			queue.poll();
		}
		return queue.offer(e);
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return queue.remove();
	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return queue.poll();
	}

	@Override
	public T element() {
		// TODO Auto-generated method stub
		return queue.element();
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return queue.peek();
	}

}
