package com.xuan.floatlayout;

import android.database.Observable;

/**
 * com.xuan.dropmenu
 *
 * @author by xuan on 2018/6/4
 * @version [版本号, 2018/6/4]
 * @update by xuan on 2018/6/4
 * @descript
 */
public class DataSetObservable extends Observable<FloatLayoutObserver> {
    /**
     * Invokes {@link FloatLayoutObserver#onChanged} on each observer.
     * Called when the contents of the data set have changed.  The recipient
     * will obtain the new contents the next time it queries the data set.
     */
    public void notifyChanged() {
        synchronized(mObservers) {
            // since onChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }
    }
    /**
     * Invokes {@link FloatLayoutObserver#onChanged} on each observer.
     * Called when the contents of the data set have changed.  The recipient
     * will obtain the new contents the next time it queries the data set.
     */
    public void notifyIsVisibleAddView() {
        synchronized(mObservers) {
            // since onChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).isVisibleAddView();
            }
        }
    }
}