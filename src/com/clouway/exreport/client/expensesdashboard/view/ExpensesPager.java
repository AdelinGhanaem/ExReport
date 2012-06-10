package com.clouway.exreport.client.expensesdashboard.view;

import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasRows;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesPager extends AbstractPager {


  private static int DEFAULT_INCREMENT = 20;

  private int lastScrollPosition = 0;

  private final ScrollPanel scrollPanel = new ScrollPanel();

  public ExpensesPager() {

    initWidget(scrollPanel);

    scrollPanel.getElement().setTabIndex(-1);

    scrollPanel.addScrollHandler(new ScrollHandler() {
      @Override
      public void onScroll(ScrollEvent event) {
        int oldScrollPosition = lastScrollPosition;
        lastScrollPosition = scrollPanel.getVerticalScrollPosition();
        if (oldScrollPosition >= lastScrollPosition) {
          return;
        }

        HasRows display = getDisplay();
        if (display == null) {
          return;
        }
        int maxScrollTop = scrollPanel.getWidget().getOffsetHeight() - scrollPanel.getOffsetHeight();

        if (lastScrollPosition >= maxScrollTop) {
          int newPageSize = Math.min(display.getVisibleRange().getLength() + DEFAULT_INCREMENT, display.getRowCount());
          display.setVisibleRange(0, newPageSize);
        }
      }
    });
  }


  public int getIncrementSize() {
    return DEFAULT_INCREMENT;
  }

  @Override
  protected void onRangeOrRowCountChanged() {

  }


  @Override
  public void setDisplay(HasRows display) {
    assert display instanceof Widget : "display must extend Widget";
    scrollPanel.setWidget((Widget) display);
    super.setDisplay(display);
  }

}
