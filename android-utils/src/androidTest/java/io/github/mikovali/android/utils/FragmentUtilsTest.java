package io.github.mikovali.android.utils;

import android.app.Application;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.mikovali.android.test.ActivityRule;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class FragmentUtilsTest {

    @Rule
    public final ActivityRule<Activity> activityRule = new ActivityRule<>(Activity.class);
    @Rule
    public final ActivityRule<ParentActivity> parentActivityRule
            = new ActivityRule<>(ParentActivity.class);
    @Rule
    public final ActivityRule<GrandParentFragmentActivity> grandParentFragmentActivityRule
            = new ActivityRule<>(GrandParentFragmentActivity.class);
    @Rule
    public final ActivityRule<ParentFragmentActivity> parentFragmentActivityRule
            = new ActivityRule<>(ParentFragmentActivity.class);

    private FragmentActivity activity;
    private FragmentActivity parentActivity;
    private FragmentActivity grandParentFragmentActivity;
    private FragmentActivity parentFragmentActivity;

    @Before
    public void setUp() {
        activity = activityRule.get();
        parentActivity = parentActivityRule.get();
        grandParentFragmentActivity = grandParentFragmentActivityRule.get();
        parentFragmentActivity = parentFragmentActivityRule.get();
    }

    @Test
    public void getParentAs() {
        // not attached fragment, can't find even the application
        Fragment fragment = new Fragment();
        assertThat(FragmentUtils.getParentAs(fragment, Application.class)).isNull();

        // attached to activity which is not parent, find Application
        fragment = getContentFragment(activity);
        assertThat(FragmentUtils.getParentAs(fragment, Parent.class)).isNull();
        assertThat(FragmentUtils.getParentAs(fragment, Application.class))
                .isSameAs(activity.getApplication());

        // attached to activity which is parent
        fragment = getContentFragment(parentActivity);
        assertThat(FragmentUtils.getParentAs(fragment, Parent.class)).isSameAs(parentActivity);

        // grand-parent fragment
        Fragment grandParentFragment = getContentFragment(grandParentFragmentActivity);
        Fragment parentFragment = getContentFragment(grandParentFragment);
        fragment = getContentFragment(parentFragment);
        assertThat(FragmentUtils.getParentAs(fragment, Parent.class)).isSameAs(grandParentFragment);

        // parent fragment
        grandParentFragment = getContentFragment(parentFragmentActivity);
        parentFragment = getContentFragment(grandParentFragment);
        fragment = getContentFragment(parentFragment);
        assertThat(FragmentUtils.getParentAs(fragment, Parent.class)).isSameAs(parentFragment);

        // target fragment
        final Fragment targetFragment = new FragmentWithParent();
        fragment.setTargetFragment(targetFragment, 0);
        assertThat(FragmentUtils.getParentAs(fragment, Parent.class)).isSameAs(targetFragment);
    }

    private static Fragment getContentFragment(FragmentActivity activity) {
        return activity.getSupportFragmentManager().findFragmentById(android.R.id.content);
    }

    private static Fragment getContentFragment(Fragment fragment) {
        return fragment.getChildFragmentManager().findFragmentById(android.R.id.content);
    }

    public interface Parent {
    }

    public static class FragmentWithView extends Fragment {
        protected Fragment getChildFragment() {
            return null;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View view = new FrameLayout(inflater.getContext());
            view.setId(android.R.id.content);
            return view;
        }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            final Fragment childFragment = getChildFragment();
            if (childFragment != null
                    && getChildFragmentManager().findFragmentById(android.R.id.content) == null) {
                getChildFragmentManager().beginTransaction()
                        .add(android.R.id.content, childFragment)
                        .commit();
            }
        }
    }

    public static class FragmentWithParent extends FragmentWithView implements Parent {
    }

    public static class Activity extends FragmentActivity {
        protected Fragment getContentFragment() {
            return new Fragment();
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, getContentFragment())
                        .commit();
            }
        }
    }

    public static class ParentActivity extends Activity implements Parent {
    }

    public static class GrandParentFragmentActivity extends ParentActivity {
        @Override
        protected Fragment getContentFragment() {
            return new FragmentWithParent() {
                @Override
                protected Fragment getChildFragment() {
                    return new FragmentWithView() {
                        @Override
                        protected Fragment getChildFragment() {
                            return new Fragment();
                        }
                    };
                }
            };
        }
    }

    public static class ParentFragmentActivity extends ParentActivity {
        @Override
        protected Fragment getContentFragment() {
            return new FragmentWithParent() {
                @Override
                protected Fragment getChildFragment() {
                    return new FragmentWithParent() {
                        @Override
                        protected Fragment getChildFragment() {
                            return new Fragment();
                        }
                    };
                }
            };
        }
    }
}
