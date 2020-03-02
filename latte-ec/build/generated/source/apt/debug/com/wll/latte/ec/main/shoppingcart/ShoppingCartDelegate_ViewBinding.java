// Generated code from Butter Knife. Do not modify!
package com.wll.latte.ec.main.shoppingcart;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.joanzapata.iconify.widget.IconTextView;
import com.wll.latte.ec.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShoppingCartDelegate_ViewBinding implements Unbinder {
  private ShoppingCartDelegate target;

  @UiThread
  public ShoppingCartDelegate_ViewBinding(ShoppingCartDelegate target, View source) {
    this.target = target;

    target.tvTopShopCartClear = Utils.findRequiredViewAsType(source, R.id.tv_top_shop_cart_clear, "field 'tvTopShopCartClear'", AppCompatTextView.class);
    target.tvTopShopCartTitle = Utils.findRequiredViewAsType(source, R.id.tv_top_shop_cart_title, "field 'tvTopShopCartTitle'", AppCompatTextView.class);
    target.tvTopShopCartRemoveSelect = Utils.findRequiredViewAsType(source, R.id.tv_top_shop_cart_remove_select, "field 'tvTopShopCartRemoveSelect'", AppCompatTextView.class);
    target.rvShopCart = Utils.findRequiredViewAsType(source, R.id.rv_shop_cart, "field 'rvShopCart'", RecyclerView.class);
    target.iconShopCarrSelectAll = Utils.findRequiredViewAsType(source, R.id.icon_shop_carr_select_all, "field 'iconShopCarrSelectAll'", IconTextView.class);
    target.rlShopCartToolbar = Utils.findRequiredViewAsType(source, R.id.rl_shop_cart_toolbar, "field 'rlShopCartToolbar'", RelativeLayout.class);
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
  }
}
