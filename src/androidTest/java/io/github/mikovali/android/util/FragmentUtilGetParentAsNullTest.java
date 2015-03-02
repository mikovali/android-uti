package io.github.mikovali.android.util;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Separate class because needs to have separate activity which does not implement the interface.
 */
public class FragmentUtilGetParentAsNullTest
        extends ActivityInstrumentationTestCase2<FragmentUtilGetParentAsNullTest.Activity> {

    private FragmentManager fragmentManager;
    private Fragment fragment;

    public FragmentUtilGetParentAsNullTest() {
        super(Activity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fragmentManager = getActivity().getSupportFragmentManager();
        fragment = new Fragment();
    }

    @UiThreadTest
    public void testGetParentAsApplicationAndNull() {
        assertThat(FragmentUtil.getParentAs(fragment, FragmentUtilTest.TYPE)).isNull();

        // attach fragment to activity which has NOT implemented the interface,
        // parent will be null
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
        fragmentManager.executePendingTransactions();
        assertThat(FragmentUtil.getParentAs(fragment, FragmentUtilTest.TYPE)).isNull();

        // search for Application type instead,
        // parent will be application
        assertThat(FragmentUtil.getParentAs(fragment, Application.class))
                .isSameAs(getActivity().getApplication());
    }

    public static class Activity extends FragmentActivity {
    }
}
