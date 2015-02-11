package io.github.mikovali.android.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class FragmentUtilTest extends ActivityInstrumentationTestCase2<FragmentUtilTest.Activity> {

    public static final Class<ParentInterface> TYPE = ParentInterface.class;

    private Activity activity;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    public FragmentUtilTest() {
        super(Activity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        fragmentManager = activity.getSupportFragmentManager();
        fragment = new Fragment();
    }

    @UiThreadTest
    public void testGetParentAsOrderOfParents() {
        // ensure unattached fragment does not have parent
        assertNull(FragmentUtil.getParentAs(fragment, TYPE));

        // attach fragment to activity which has implemented the interface,
        // parent will be the activity
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
        fragmentManager.executePendingTransactions();
        assertSame(activity, FragmentUtil.getParentAs(fragment, TYPE));

        // grandparent fragment implements the interface, parent fragment doesn't,
        // parent will be grandparent fragment
        final Fragment grandparentFragment = new FragmentWithParentInterface();
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, grandparentFragment)
                .commit();
        fragmentManager.executePendingTransactions();
        Fragment parentFragment = new FragmentWithView();
        FragmentManager fm = grandparentFragment.getChildFragmentManager();
        fm.beginTransaction()
                .replace(android.R.id.content, parentFragment)
                .commit();
        fm.executePendingTransactions();
        fm = parentFragment.getChildFragmentManager();
        fm.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
        fm.executePendingTransactions();
        assertSame(grandparentFragment, FragmentUtil.getParentAs(fragment, TYPE));

        // parent fragment now implements the interface,
        // parent will be parent fragment
        parentFragment = new FragmentWithParentInterface();
        fm = grandparentFragment.getChildFragmentManager();
        fm.beginTransaction()
                .replace(android.R.id.content, parentFragment)
                .commit();
        fm.executePendingTransactions();
        fm = parentFragment.getChildFragmentManager();
        fm.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
        fm.executePendingTransactions();
        assertSame(parentFragment, FragmentUtil.getParentAs(fragment, TYPE));

        // set target fragment as implementing the interface,
        // parent will be target fragment
        final Fragment targetFragment = new FragmentWithParentInterface();
        fragment.setTargetFragment(targetFragment, 0);
        assertSame(targetFragment, FragmentUtil.getParentAs(fragment, TYPE));
    }

    public static class Activity extends FragmentActivity implements ParentInterface {
    }

    public static interface ParentInterface {
    }

    public static class FragmentWithView extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            final FrameLayout view = new FrameLayout(inflater.getContext());
            view.setId(android.R.id.content);
            return view;
        }
    }

    public static class FragmentWithParentInterface extends FragmentWithView implements ParentInterface {
    }
}
