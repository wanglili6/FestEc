// Generated code from Butter Knife. Do not modify!
package com.wll.latte.ec.main.shoppingcart;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShoppingCartDelegate_ViewBinding implements Unbinder {
  private ShoppingCartDelegate target;

  private View view6f9;

  private View view6fa;

  private View view660;

  private View view6f5;

  @UiThread
  public ShoppingCartDelegate_ViewBinding(final ShoppingCartDelegate target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_top_shop_cart_clear, "field 'tvTopShopCartClear' and method 'onViewClicked'");
    target.tvTopShopCartClear = Utils.castView(view, R.id.tv_top_shop_cart_clear, "field 'tvTopShopCartClear'", AppCompatTextView.class);
    view6f9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTopShopCartTitle = Utils.findRequiredViewAsType(source, R.id.tv_top_shop_cart_title, "field 'tvTopShopCartTitle'", AppCompatTextView.class);
    view = Utils.findRequiredView(source, R.id.tv_top_shop_cart_remove_select, "field 'tvTopShopCartRemoveSelect' and method 'onViewClicked'");
    target.tvTopShopCartRemoveSelect = Utils.castView(view, R.id.tv_top_shop_cart_remove_select, "field 'tvTopShopCartRemoveSelect'", AppCompatTextView.class);
    view6fa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvShopCart = Utils.findRequiredViewAsType(source, R.id.rv_shop_cart, "field 'rvShopCart'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.icon_shop_cart_select_all, "field 'iconShopCarrSelectAll' and method 'onViewClicked'");
    target.iconShopCarrSelectAll = Utils.castView(view, R.id.icon_shop_cart_select_all, "field 'iconShopCarrSelectAll'", IconTextView.class);
    view660 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlShopCartToolbar = Utils.findRequiredViewAsType(source, R.id.rl_shop_cart_toolbar, "field 'rlShopCartToolbar'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_settlement, "field 'tvSettlement' and method 'onViewClicked'");
    target.tvSettlement = Utils.castView(view, R.id.tv_settlement, "field 'tvSettlement'", AppCompatTextView.class);
    view6f5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ShoppingCartDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTopShopCartClear = null;
    target.tvTopShopCartTitle = null;
    target.tvTopShopCartRemoveSelect = null;
    target.rvShopCart = null;
    target.iconShopCarrSelectAll = null;
    target.rlShopCartToolbar = null;
    target.tvSettlement = null;

    view6f9.setOnClickListener(null);
    view6f9 = null;
    view6fa.setOnClickListener(null);
    view6fa = null;
    view660.setOnClickListener(null);
    view660 = null;
    view6f5.setOnClickListener(null);
    view6f5 = null;
  }
}
