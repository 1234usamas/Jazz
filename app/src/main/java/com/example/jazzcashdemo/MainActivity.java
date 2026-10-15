package com.example.jazzcashdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends Activity {
    private static final int INK = Color.rgb(39, 37, 43);
    private static final int MUTED = Color.rgb(119, 116, 122);
    private static final int RED = Color.rgb(215, 25, 63);
    private static final int CANVAS = Color.rgb(248, 247, 248);
    private LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(34, 34, 34));
        getWindow().setNavigationBarColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                ? Color.WHITE : INK);
        showHome();
    }

    private void showHome() {
        getWindow().setStatusBarColor(Color.rgb(34, 34, 34));
        LinearLayout screen = new LinearLayout(this);
        screen.setOrientation(LinearLayout.VERTICAL);
        screen.setBackgroundColor(Color.rgb(245, 245, 245));

        ScrollView scroll = new ScrollView(this);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setBackgroundColor(Color.rgb(245, 245, 245));
        scroll.addView(page);
        addReferenceHeader(page);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(16), dp(18), dp(16), dp(22));
        content.setBackground(topRoundedBackground(Color.rgb(245, 245, 245), dp(26)));
        LinearLayout.LayoutParams bodyParams = new LinearLayout.LayoutParams(-1, -2);
        bodyParams.topMargin = -dp(22);
        page.addView(content, bodyParams);

        addReferenceServices();
        addOffers();
        addRecentActivity();

        scroll.setClipToPadding(false);
        screen.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));
        screen.addView(makeBottomNavigation(false), new LinearLayout.LayoutParams(-1, dp(70)));
        setContentView(screen);
    }

    private void addReferenceHeader(LinearLayout parent) {
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.VERTICAL);
        header.setPadding(dp(22), dp(8), dp(22), dp(31));
        header.setBackgroundColor(Color.rgb(34, 34, 34));

        LinearLayout brandRow = new LinearLayout(this);
        brandRow.setGravity(Gravity.CENTER_VERTICAL);
        TextView brand = text("JazzCash", 21, Color.WHITE, true);
        brandRow.addView(brand, new LinearLayout.LayoutParams(0, dp(43), 1));
        TextView preview = text("PREVIEW", 9, Color.rgb(255, 210, 53), true);
        preview.setPadding(dp(7), dp(4), dp(7), dp(4));
        preview.setBackground(roundedBackground(Color.rgb(62, 56, 38), dp(12)));
        brandRow.addView(preview);
        TextView help = text("?", 17, Color.WHITE, true);
        help.setGravity(Gravity.CENTER);
        brandRow.addView(help, new LinearLayout.LayoutParams(dp(38), dp(42)));
        TextView bell = text("●", 14, Color.rgb(255, 193, 7), true);
        bell.setGravity(Gravity.CENTER);
        brandRow.addView(bell, new LinearLayout.LayoutParams(dp(32), dp(42)));
        header.addView(brandRow);

        LinearLayout greetingRow = new LinearLayout(this);
        greetingRow.setGravity(Gravity.CENTER_VERTICAL);
        greetingRow.setPadding(0, dp(13), 0, 0);
        TextView avatar = text("U", 15, Color.WHITE, true);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackground(circleBackground(Color.rgb(103, 103, 103)));
        greetingRow.addView(avatar, new LinearLayout.LayoutParams(dp(38), dp(38)));
        LinearLayout greeting = new LinearLayout(this);
        greeting.setOrientation(LinearLayout.VERTICAL);
        greeting.setPadding(dp(10), 0, 0, 0);
        greeting.addView(text("Usama", 16, Color.WHITE, true));
        greeting.addView(text("✦  50,000 points", 12, Color.rgb(214, 214, 214), false));
        greetingRow.addView(greeting, new LinearLayout.LayoutParams(0, -2, 1));
        TextView loan = text("Get Rs. 10,000 loan", 10, Color.rgb(244, 193, 34), true);
        loan.setPadding(dp(10), dp(8), dp(10), dp(8));
        loan.setBackground(roundedBackground(Color.rgb(58, 55, 45), dp(20)));
        greetingRow.addView(loan);
        header.addView(greetingRow);

        LinearLayout balance = new LinearLayout(this);
        balance.setGravity(Gravity.BOTTOM);
        TextView amount = text("Rs. 1,91,450", 28, Color.rgb(255, 195, 35), true);
        balance.addView(amount);
        TextView cents = text(".00", 15, Color.rgb(255, 195, 35), true);
        LinearLayout.LayoutParams centsParams = new LinearLayout.LayoutParams(-2, -2);
        centsParams.bottomMargin = dp(3);
        balance.addView(cents, centsParams);
        LinearLayout.LayoutParams balanceParams = new LinearLayout.LayoutParams(-1, -2);
        balanceParams.topMargin = dp(22);
        header.addView(balance, balanceParams);

        LinearLayout actions = new LinearLayout(this);
        actions.setGravity(Gravity.CENTER_VERTICAL);
        TextView addMoney = text("+  Add money", 14, Color.WHITE, true);
        addMoney.setGravity(Gravity.CENTER);
        addMoney.setBackground(roundedBackground(Color.rgb(186, 38, 40), dp(24)));
        addMoney.setOnClickListener(v -> showDemoMessage("Add money preview."));
        actions.addView(addMoney, new LinearLayout.LayoutParams(0, dp(43), 1));
        TextView account = text("▤  My account", 14, Color.WHITE, true);
        account.setGravity(Gravity.CENTER);
        account.setBackground(roundedBackground(Color.rgb(105, 105, 105), dp(24)));
        account.setOnClickListener(v -> showDemoMessage("Account preview."));
        LinearLayout.LayoutParams accountParams = new LinearLayout.LayoutParams(0, dp(43), 1);
        accountParams.leftMargin = dp(12);
        actions.addView(account, accountParams);
        LinearLayout.LayoutParams actionsParams = new LinearLayout.LayoutParams(-1, -2);
        actionsParams.topMargin = dp(23);
        header.addView(actions, actionsParams);
        parent.addView(header, new LinearLayout.LayoutParams(-1, -2));
    }
    private void addReferenceServices() {
        addSectionHeading("My JazzCash", "Edit");
        addServiceGridRow(new String[]{"plane", "bus", "phone", "wallet"},
                new String[]{"Money Transfer", "Bus Tickets", "Mobile Load", "Bulk Payments"},
                new View.OnClickListener[]{
                        v -> showSendMoney(),
                        v -> showDemoMessage("Bus ticket preview."),
                        v -> openPaymentDialog("Mobile load"),
                        v -> openPaymentDialog("Bulk payment")});
        addSectionHeading("Top Picks for You", "");
        addServiceGridRow(new String[]{"food", "car", "request", "school"},
                new String[]{"Food Delivery", "M-Tag Recharge", "Request Money", "School Fees"},
                new View.OnClickListener[]{
                        v -> showDemoMessage("Food delivery preview."),
                        v -> showDemoMessage("M-Tag recharge preview."),
                        v -> showDemoMessage("Request money preview."),
                        v -> openPaymentDialog("School fees")});
    }

    private void addSectionHeading(String heading, String action) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        TextView title = text(heading, 17, Color.BLACK, true);
        row.addView(title, new LinearLayout.LayoutParams(0, -2, 1));
        if (!action.isEmpty()) {
            TextView edit = text("✎  " + action, 12, INK, false);
            edit.setPadding(dp(12), dp(7), dp(12), dp(7));
            edit.setBackground(roundedBackground(Color.rgb(238, 238, 238), dp(18)));
            edit.setOnClickListener(v -> showDemoMessage("Home shortcuts preview."));
            row.addView(edit);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = dp(10);
        content.addView(row, params);
    }

    private void addServiceGridRow(String[] icons, String[] labels,
                                   View.OnClickListener[] listeners) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.TOP);
        for (int i = 0; i < labels.length; i++) {
            LinearLayout tile = new LinearLayout(this);
            tile.setOrientation(LinearLayout.VERTICAL);
            tile.setGravity(Gravity.CENTER);
            tile.setPadding(dp(2), dp(2), dp(2), dp(4));
            tile.setOnClickListener(listeners[i]);

            ServiceGlyph glyph = new ServiceGlyph(icons[i]);
            glyph.setBackground(roundedBackground(Color.WHITE, dp(14)));
            glyph.setElevation(dp(1));
            tile.addView(glyph, new LinearLayout.LayoutParams(dp(56), dp(56)));
            TextView label = text(labels[i], 11, Color.rgb(55, 55, 55), false);
            label.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(-1, -2);
            labelParams.topMargin = dp(6);
            tile.addView(label, labelParams);

            LinearLayout.LayoutParams tileParams = new LinearLayout.LayoutParams(0, dp(99), 1);
            tileParams.setMargins(dp(1), 0, dp(1), 0);
            row.addView(tile, tileParams);
        }
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(-1, -2);
        rowParams.bottomMargin = dp(7);
        content.addView(row, rowParams);
    }
    private void addOffers() {
        addSectionTitle("Explore more");
        android.widget.HorizontalScrollView horizontal = new android.widget.HorizontalScrollView(this);
        horizontal.setHorizontalScrollBarEnabled(false);
        LinearLayout cards = new LinearLayout(this);
        cards.setOrientation(LinearLayout.HORIZONTAL);
        addOfferCard(cards, "Mobile bundles", "Find a package", Color.rgb(246, 145, 49));
        addOfferCard(cards, "Pay a bill", "Quick bill preview", Color.rgb(234, 183, 40));
        addOfferCard(cards, "Rewards", "Explore offers", Color.rgb(91, 107, 196));
        horizontal.addView(cards);
        content.addView(horizontal);
    }

    private void addOfferCard(LinearLayout parent, String title, String subtitle, int color) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp(14), dp(12), dp(14), dp(12));
        card.setBackground(roundedBackground(color, dp(16)));
        card.addView(text(title, 14, Color.WHITE, true));
        TextView detail = text(subtitle, 11, Color.WHITE, false);
        LinearLayout.LayoutParams detailParams = new LinearLayout.LayoutParams(-1, -2);
        detailParams.topMargin = dp(5);
        card.addView(detail, detailParams);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(dp(166), dp(78));
        cardParams.setMargins(0, dp(2), dp(10), dp(4));
        parent.addView(card, cardParams);
    }

    private void addHeader() {
        LinearLayout header = new LinearLayout(this);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setPadding(0, 0, 0, dp(18));

        LinearLayout greeting = new LinearLayout(this);
        greeting.setOrientation(LinearLayout.VERTICAL);
        TextView welcome = text("Assalam-o-Alaikum,", 13, MUTED, false);
        TextView name = text("Usama", 21, INK, true);
        greeting.addView(welcome);
        greeting.addView(name);
        header.addView(greeting, new LinearLayout.LayoutParams(0, -2, 1));

        TextView avatar = text("U", 18, RED, true);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackground(circleBackground(Color.rgb(255, 231, 236)));
        header.addView(avatar, new LinearLayout.LayoutParams(dp(44), dp(44)));
        content.addView(header);
    }

    private void addWalletCard() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(20), dp(20), dp(20), dp(19));
        card.setBackground(roundedBackground(
                new int[]{Color.rgb(222, 31, 69), Color.rgb(174, 13, 47)}, dp(20)));

        LinearLayout labelRow = new LinearLayout(this);
        labelRow.setGravity(Gravity.CENTER_VERTICAL);
        TextView walletLabel = text("MY MOBILE WALLET", 12, Color.WHITE, true);
        walletLabel.setLetterSpacing(0.08f);
        labelRow.addView(walletLabel, new LinearLayout.LayoutParams(0, -2, 1));
        TextView previewBadge = text("PREVIEW", 10, Color.WHITE, true);
        previewBadge.setPadding(dp(10), dp(5), dp(10), dp(5));
        previewBadge.setBackground(roundedBackground(Color.rgb(194, 18, 54), dp(14)));
        labelRow.addView(previewBadge);
        card.addView(labelRow);

        TextView balance = text("Rs. 24,580.00", 30, Color.WHITE, true);
        LinearLayout.LayoutParams balanceParams = new LinearLayout.LayoutParams(-2, -2);
        balanceParams.topMargin = dp(15);
        card.addView(balance, balanceParams);

        TextView number = text("0300 •••• 1234", 14, Color.rgb(255, 224, 231), false);
        LinearLayout.LayoutParams numberParams = new LinearLayout.LayoutParams(-2, -2);
        numberParams.topMargin = dp(6);
        card.addView(number, numberParams);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(24);
        content.addView(card, cardParams);
    }

    private void addServices() {
        addSectionTitle("What would you like to do?");
        LinearLayout firstRow = new LinearLayout(this);
        firstRow.setGravity(Gravity.CENTER_VERTICAL);
        firstRow.addView(serviceTile("↗", "Send money", RED, v -> openPaymentDialog("Send money")));
        firstRow.addView(serviceTile("▣", "Mobile load", Color.rgb(237, 133, 31),
                v -> openPaymentDialog("Mobile load")));
        content.addView(firstRow);

        LinearLayout secondRow = new LinearLayout(this);
        secondRow.setGravity(Gravity.CENTER_VERTICAL);
        secondRow.addView(serviceTile("▤", "Pay bills", Color.rgb(58, 132, 104),
                v -> openPaymentDialog("Pay bills")));
        secondRow.addView(serviceTile("▦", "Scan QR", Color.rgb(91, 104, 173),
                v -> showDemoMessage("QR payments are not available in this preview.")));
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(-1, -2);
        rowParams.topMargin = dp(12);
        content.addView(secondRow, rowParams);
    }

    private View serviceTile(String icon, String label, int color, View.OnClickListener listener) {
        LinearLayout tile = new LinearLayout(this);
        tile.setOrientation(LinearLayout.VERTICAL);
        tile.setGravity(Gravity.CENTER_VERTICAL);
        tile.setPadding(dp(14), dp(14), dp(10), dp(14));
        tile.setBackground(roundedBackground(Color.WHITE, dp(16)));
        tile.setElevation(dp(1));
        tile.setOnClickListener(listener);

        TextView iconView = text(icon, 21, color, true);
        iconView.setGravity(Gravity.CENTER);
        iconView.setBackground(circleBackground(tint(color, 0.12f)));
        tile.addView(iconView, new LinearLayout.LayoutParams(dp(42), dp(42)));

        TextView title = text(label, 14, INK, true);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(11);
        tile.addView(title, titleParams);

        LinearLayout.LayoutParams tileParams = new LinearLayout.LayoutParams(0, dp(112), 1);
        tileParams.setMargins(dp(4), dp(4), dp(8), dp(4));
        return wrapInWeightedFrame(tile, tileParams);
    }

    private View wrapInWeightedFrame(View view, LinearLayout.LayoutParams params) {
        android.widget.FrameLayout frame = new android.widget.FrameLayout(this);
        frame.addView(view, new android.widget.FrameLayout.LayoutParams(-1, -1));
        frame.setLayoutParams(params);
        return frame;
    }

    private void addRecentActivity() {
        LinearLayout titleRow = new LinearLayout(this);
        titleRow.setGravity(Gravity.CENTER_VERTICAL);
        TextView title = text("Recent activity", 18, INK, true);
        titleRow.addView(title, new LinearLayout.LayoutParams(0, -2, 1));
        TextView seeAll = text("See all", 13, RED, true);
        seeAll.setOnClickListener(v -> showActivity());
        titleRow.addView(seeAll);

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(25);
        titleParams.bottomMargin = dp(10);
        content.addView(titleRow, titleParams);

        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        list.setPadding(dp(14), dp(4), dp(14), dp(4));
        list.setBackground(roundedBackground(Color.WHITE, dp(16)));
        addTransactionRow(list, "↑", "Sent to Ali", "Today · 10:42 AM", "− Rs. 1,500", false);
        addDivider(list);
        addTransactionRow(list, "▣", "Mobile load", "Yesterday · 06:15 PM", "− Rs. 500", false);
        addDivider(list);
        addTransactionRow(list, "↓", "Received from Sara", "12 Oct · 02:30 PM", "+ Rs. 3,000", true);
        content.addView(list);
    }

    private void addTransactionRow(LinearLayout list, String icon, String title, String date,
                                  String amount, boolean incoming) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, dp(12), 0, dp(12));
        TextView iconView = text(icon, 18, incoming ? Color.rgb(42, 143, 101) : RED, true);
        iconView.setGravity(Gravity.CENTER);
        iconView.setBackground(circleBackground(incoming
                ? Color.rgb(230, 246, 239) : Color.rgb(255, 235, 239)));
        row.addView(iconView, new LinearLayout.LayoutParams(dp(42), dp(42)));

        LinearLayout description = new LinearLayout(this);
        description.setOrientation(LinearLayout.VERTICAL);
        description.setPadding(dp(11), 0, 0, 0);
        description.addView(text(title, 14, INK, true));
        description.addView(text(date, 11, MUTED, false));
        LinearLayout.LayoutParams descriptionParams = new LinearLayout.LayoutParams(0, -2, 1);
        row.addView(description, descriptionParams);

        TextView value = text(amount, 13, incoming ? Color.rgb(42, 143, 101) : INK, true);
        row.addView(value);
        list.addView(row);
    }

    private void addDivider(LinearLayout parent) {
        View divider = new View(this);
        divider.setBackgroundColor(Color.rgb(241, 239, 241));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, dp(1));
        parent.addView(divider, params);
    }

    private View makeBottomNavigation(boolean activitySelected) {
        LinearLayout nav = new LinearLayout(this);
        nav.setGravity(Gravity.CENTER);
        nav.setPadding(dp(5), dp(5), dp(5), dp(4));
        nav.setBackgroundColor(Color.WHITE);
        nav.setElevation(dp(8));
        nav.addView(navItem("home", "Home", !activitySelected, v -> showHome()),
                new LinearLayout.LayoutParams(0, -1, 1));
        nav.addView(navItem("locator", "Locator", false,
                v -> showDemoMessage("Locator preview.")), new LinearLayout.LayoutParams(0, -1, 1));
        nav.addView(navItem("qr", "Scan QR", false,
                v -> showDemoMessage("QR preview.")), new LinearLayout.LayoutParams(0, -1, 1));
        nav.addView(navItem("search", "Search", false,
                v -> showDemoMessage("Search preview.")), new LinearLayout.LayoutParams(0, -1, 1));
        nav.addView(navItem("invite", "Invite & Earn", false,
                v -> showDemoMessage("Invite preview.")), new LinearLayout.LayoutParams(0, -1, 1));
        return nav;
    }

    private View navItem(String icon, String label, boolean selected,
                         View.OnClickListener listener) {
        LinearLayout item = new LinearLayout(this);
        item.setOrientation(LinearLayout.VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setOnClickListener(listener);
        ServiceGlyph glyph = new ServiceGlyph(icon);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(dp(24), dp(24));
        item.addView(glyph, iconParams);
        TextView labelView = text(label, 9, selected ? INK : MUTED, selected);
        labelView.setGravity(Gravity.CENTER);
        item.addView(labelView);
        return item;
    }
    private void showActivity() {
        LinearLayout screen = new LinearLayout(this);
        screen.setOrientation(LinearLayout.VERTICAL);
        screen.setBackgroundColor(CANVAS);

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(20), dp(22), dp(20), dp(18));
        TextView title = text("Activity", 24, INK, true);
        page.addView(title);
        TextView subtitle = text("Sample transaction history", 14, MUTED, false);
        LinearLayout.LayoutParams subtitleParams = new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(5);
        subtitleParams.bottomMargin = dp(18);
        page.addView(subtitle, subtitleParams);

        LinearLayout list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        list.setPadding(dp(14), dp(4), dp(14), dp(4));
        list.setBackground(roundedBackground(Color.WHITE, dp(16)));
        addTransactionRow(list, "↑", "Sent to Ali", "Today · 10:42 AM", "− Rs. 1,500", false);
        addDivider(list);
        addTransactionRow(list, "▣", "Mobile load", "Yesterday · 06:15 PM", "− Rs. 500", false);
        addDivider(list);
        addTransactionRow(list, "↓", "Received from Sara", "12 Oct · 02:30 PM", "+ Rs. 3,000", true);
        page.addView(list);
        screen.addView(page, new LinearLayout.LayoutParams(-1, 0, 1));
        screen.addView(makeBottomNavigation(true), new LinearLayout.LayoutParams(-1, dp(70)));
        setContentView(screen);
    }

    private void addSectionTitle(String value) {
        TextView title = text(value, 18, INK, true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = dp(10);
        content.addView(title, params);
    }

    private void openPaymentDialog(String action) {
        LinearLayout form = new LinearLayout(this);
        form.setOrientation(LinearLayout.VERTICAL);
        int padding = dp(20);
        form.setPadding(padding, dp(8), padding, 0);

        EditText phone = new EditText(this);
        phone.setSingleLine(true);
        phone.setHint(action.equals("Pay bills") ? "Consumer / account number" : "Mobile number (e.g. 03001234567)");
        phone.setInputType(InputType.TYPE_CLASS_PHONE);
        form.addView(phone, new LinearLayout.LayoutParams(-1, -2));

        EditText amount = new EditText(this);
        amount.setSingleLine(true);
        amount.setHint("Amount in Rs.");
        amount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        LinearLayout.LayoutParams amountParams = new LinearLayout.LayoutParams(-1, -2);
        amountParams.topMargin = dp(8);
        form.addView(amount, amountParams);

        new AlertDialog.Builder(this)
                .setTitle(action + " · Preview")
                .setMessage("Enter the recipient and amount to continue.")
                .setView(form)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Find recipient", (dialog, which) ->
                        lookupDemoRecipient(action, phone.getText().toString().trim(),
                                amount.getText().toString().trim()))
                .show();
    }

    private void showSendMoney() {
        getWindow().setStatusBarColor(Color.rgb(34, 34, 34));
        LinearLayout screen = new LinearLayout(this);
        screen.setOrientation(LinearLayout.VERTICAL);
        screen.setBackgroundColor(Color.rgb(244, 244, 244));

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setBackgroundColor(Color.rgb(244, 244, 244));
        scroll.addView(page);

        LinearLayout darkHeader = new LinearLayout(this);
        darkHeader.setOrientation(LinearLayout.VERTICAL);
        darkHeader.setPadding(dp(20), dp(8), dp(20), dp(25));
        darkHeader.setBackground(roundedBackground(Color.rgb(34, 34, 34), dp(0)));

        LinearLayout toolbar = new LinearLayout(this);
        toolbar.setGravity(Gravity.CENTER_VERTICAL);
        TextView back = text("‹", 32, Color.WHITE, false);
        back.setGravity(Gravity.CENTER);
        back.setOnClickListener(v -> {
            getWindow().setStatusBarColor(Color.rgb(34, 34, 34));
            showHome();
        });
        toolbar.addView(back, new LinearLayout.LayoutParams(dp(42), dp(48)));
        TextView title = text("Money Transfer", 19, Color.WHITE, true);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(0, -2, 1);
        titleParams.leftMargin = dp(8);
        toolbar.addView(title, titleParams);
        TextView help = text("?", 18, Color.WHITE, true);
        help.setGravity(Gravity.CENTER);
        help.setBackground(circleBackground(Color.rgb(70, 70, 70)));
        help.setOnClickListener(v -> showDemoMessage("Money transfer preview help."));
        toolbar.addView(help, new LinearLayout.LayoutParams(dp(34), dp(34)));
        darkHeader.addView(toolbar);

        TextView caption = text("Send money", 24, Color.WHITE, true);
        LinearLayout.LayoutParams captionParams = new LinearLayout.LayoutParams(-1, -2);
        captionParams.topMargin = dp(15);
        darkHeader.addView(caption, captionParams);
        darkHeader.addView(text("To a JazzCash account", 14, Color.rgb(205, 205, 205), false));

        LinearLayout balanceRow = new LinearLayout(this);
        balanceRow.setGravity(Gravity.CENTER_VERTICAL);
        balanceRow.setPadding(dp(15), dp(13), dp(15), dp(13));
        balanceRow.setBackground(roundedBackground(Color.rgb(47, 47, 47), dp(12)));
        LinearLayout balanceLabels = new LinearLayout(this);
        balanceLabels.setOrientation(LinearLayout.VERTICAL);
        balanceLabels.addView(text("Available balance", 12, Color.LTGRAY, false));
        balanceLabels.addView(text("Rs. 1,91,450.00", 20, Color.rgb(255, 201, 35), true));
        balanceRow.addView(balanceLabels, new LinearLayout.LayoutParams(0, -2, 1));
        TextView accountBadge = text("MY ACCOUNT", 10, Color.WHITE, true);
        accountBadge.setPadding(dp(12), dp(9), dp(12), dp(9));
        accountBadge.setBackground(roundedBackground(Color.rgb(91, 91, 91), dp(22)));
        balanceRow.addView(accountBadge);
        LinearLayout.LayoutParams balanceParams = new LinearLayout.LayoutParams(-1, -2);
        balanceParams.topMargin = dp(18);
        darkHeader.addView(balanceRow, balanceParams);
        page.addView(darkHeader);

        LinearLayout form = new LinearLayout(this);
        form.setOrientation(LinearLayout.VERTICAL);
        form.setPadding(dp(20), dp(22), dp(20), dp(24));
        form.setBackground(topRoundedBackground(Color.WHITE, dp(22)));
        LinearLayout.LayoutParams formParams = new LinearLayout.LayoutParams(-1, -2);
        formParams.topMargin = -dp(12);
        page.addView(form, formParams);

        form.addView(text("Choose transfer type", 16, INK, true));
        LinearLayout typeRow = new LinearLayout(this);
        typeRow.setGravity(Gravity.CENTER_VERTICAL);
        typeRow.setPadding(dp(14), dp(13), dp(14), dp(13));
        typeRow.setBackground(roundedBackground(Color.rgb(255, 244, 225), dp(12)));
        TextView walletIcon = text("▣", 22, RED, true);
        walletIcon.setGravity(Gravity.CENTER);
        typeRow.addView(walletIcon, new LinearLayout.LayoutParams(dp(36), dp(36)));
        LinearLayout walletDescription = new LinearLayout(this);
        walletDescription.setOrientation(LinearLayout.VERTICAL);
        walletDescription.setPadding(dp(10), 0, 0, 0);
        walletDescription.addView(text("JazzCash Account", 15, INK, true));
        walletDescription.addView(text("Instant wallet transfer preview", 12, MUTED, false));
        typeRow.addView(walletDescription, new LinearLayout.LayoutParams(0, -2, 1));
        typeRow.addView(text("⌄", 20, MUTED, true));
        LinearLayout.LayoutParams typeParams = new LinearLayout.LayoutParams(-1, -2);
        typeParams.topMargin = dp(12);
        typeParams.bottomMargin = dp(23);
        form.addView(typeRow, typeParams);

        form.addView(text("Recipient mobile number", 14, INK, true));
        EditText phone = transferInput("03XX XXXXXXX", InputType.TYPE_CLASS_PHONE);
        LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(-1, dp(56));
        inputParams.topMargin = dp(8);
        form.addView(phone, inputParams);
        TextView contactHint = text("Enter 03001234567 to view the sample recipient", 12, MUTED, false);
        LinearLayout.LayoutParams hintParams = new LinearLayout.LayoutParams(-1, -2);
        hintParams.topMargin = dp(7);
        form.addView(contactHint, hintParams);

        TextView amountLabel = text("Amount", 14, INK, true);
        LinearLayout.LayoutParams amountLabelParams = new LinearLayout.LayoutParams(-1, -2);
        amountLabelParams.topMargin = dp(23);
        form.addView(amountLabel, amountLabelParams);
        EditText amount = transferInput("Rs. 0.00", InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        LinearLayout.LayoutParams amountInputParams = new LinearLayout.LayoutParams(-1, dp(56));
        amountInputParams.topMargin = dp(8);
        form.addView(amount, amountInputParams);

        TextView notice = text("PREVIEW ONLY  ·  No money will be sent", 12,
                Color.rgb(113, 77, 0), true);
        notice.setGravity(Gravity.CENTER);
        notice.setPadding(dp(12), dp(12), dp(12), dp(12));
        notice.setBackground(roundedBackground(Color.rgb(255, 246, 216), dp(10)));
        LinearLayout.LayoutParams noticeParams = new LinearLayout.LayoutParams(-1, -2);
        noticeParams.topMargin = dp(20);
        form.addView(notice, noticeParams);

        TextView continueButton = text("Continue", 16, Color.WHITE, true);
        continueButton.setGravity(Gravity.CENTER);
        continueButton.setBackground(roundedBackground(RED, dp(28)));
        continueButton.setOnClickListener(v -> lookupDemoRecipient("Send money",
                phone.getText().toString().trim(), amount.getText().toString().trim()));
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(-1, dp(54));
        buttonParams.topMargin = dp(16);
        form.addView(continueButton, buttonParams);

        screen.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));
        screen.addView(makeBottomNavigation(false), new LinearLayout.LayoutParams(-1, dp(70)));
        setContentView(screen);
    }

    private EditText transferInput(String hint, int inputType) {
        EditText input = new EditText(this);
        input.setSingleLine(true);
        input.setTextSize(16);
        input.setTextColor(INK);
        input.setHintTextColor(MUTED);
        input.setHint(hint);
        input.setInputType(inputType);
        input.setPadding(dp(15), 0, dp(15), 0);
        input.setBackground(roundedBackground(Color.rgb(248, 248, 248), dp(11)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            input.setTextCursorDrawable(null);
        }
        return input;
    }

    private void lookupDemoRecipient(String action, String phone, String rawAmount) {
        if (phone.isEmpty() || rawAmount.isEmpty()) {
            showDemoMessage("Please enter a recipient and amount.");
            return;
        }
        // Local interview-demo fixture only. It is not a JazzCash account lookup.
        if (!phone.equals("03001234567")) {
            showDemoMessage("No preview match. Try 03001234567.");
            return;
        }
        double amount;
        try {
            amount = Double.parseDouble(rawAmount);
        } catch (NumberFormatException exception) {
            showDemoMessage("Enter a valid amount.");
            return;
        }
        if (amount <= 0 || Double.isInfinite(amount) || Double.isNaN(amount)) {
            showDemoMessage("Amount must be greater than zero.");
            return;
        }

        String formattedAmount = NumberFormat.getNumberInstance(Locale.US).format(amount);
        new AlertDialog.Builder(this)
                .setTitle("Confirm recipient")
                .setMessage("Ayesha Khan\nJazzCash wallet - " + phone
                        + "\n\n" + action + " - Rs. " + formattedAmount
                        + "\n\nPreview name · not verified with JazzCash")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Continue", (dialog, which) ->
                        confirmDemoTransaction(action, phone, "Ayesha Khan", formattedAmount))
                .show();
    }

    private void confirmDemoTransaction(String action, String phone, String recipientName,
                                        String formattedAmount) {
        new AlertDialog.Builder(this)
                .setTitle("Transfer preview")
                .setMessage(action + " of Rs. " + formattedAmount + " to " + recipientName
                        + " (" + phone + ") is simulated only. No money was sent or charged.")
                .setPositiveButton("Done", null)
                .show();
    }

    private void showDemoMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private final class ServiceGlyph extends View {
        private final String kind;
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ServiceGlyph(String kind) {
            super(MainActivity.this);
            this.kind = kind;
            paint.setColor(Color.rgb(74, 78, 82));
            paint.setStrokeWidth(2.2f);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.save();
            canvas.scale(getWidth() / 48f, getHeight() / 48f);
            switch (kind) {
                case "home": drawHome(canvas); break;
                case "locator": drawLocator(canvas); break;
                case "qr": drawQr(canvas); break;
                case "search": drawSearch(canvas); break;
                case "invite": drawInvite(canvas); break;
                case "plane": drawPlane(canvas); break;
                case "bus": drawBus(canvas); break;
                case "phone": drawPhone(canvas); break;
                case "wallet": drawWallet(canvas); break;
                case "food": drawFood(canvas); break;
                case "car": drawCar(canvas); break;
                case "request": drawRequest(canvas); break;
                default: drawSchool(canvas); break;
            }
            canvas.restore();
        }

        private void drawHome(Canvas c) {
            Path p = new Path();
            p.moveTo(7, 22); p.lineTo(24, 8); p.lineTo(41, 22);
            c.drawPath(p, paint);
            c.drawRect(12, 21, 36, 41, paint);
            c.drawRect(21, 29, 27, 41, paint);
        }

        private void drawLocator(Canvas c) {
            c.drawCircle(24, 20, 13, paint);
            c.drawCircle(24, 20, 4, paint);
            Path p = new Path();
            p.moveTo(14, 29); p.lineTo(24, 44); p.lineTo(34, 29);
            c.drawPath(p, paint);
        }

        private void drawQr(Canvas c) {
            c.drawRect(7, 7, 20, 20, paint);
            c.drawRect(28, 7, 41, 20, paint);
            c.drawRect(7, 28, 20, 41, paint);
            c.drawRect(11, 11, 16, 16, paint);
            c.drawRect(32, 11, 37, 16, paint);
            c.drawRect(11, 32, 16, 37, paint);
            c.drawRect(29, 29, 34, 34, paint);
            c.drawRect(37, 37, 41, 41, paint);
        }

        private void drawSearch(Canvas c) {
            c.drawCircle(20, 20, 12, paint);
            c.drawLine(29, 29, 41, 41, paint);
        }

        private void drawInvite(Canvas c) {
            c.drawCircle(18, 15, 7, paint);
            c.drawArc(new RectF(6, 25, 30, 44), 180, 180, false, paint);
            c.drawCircle(36, 31, 8, paint);
            c.drawLine(36, 27, 36, 35, paint);
            c.drawLine(32, 31, 40, 31, paint);
        }
        private void drawPlane(Canvas c) {
            Path p = new Path();
            p.moveTo(7, 26); p.lineTo(41, 8); p.lineTo(29, 40);
            p.lineTo(22, 29); p.close();
            c.drawPath(p, paint);
            c.drawLine(22, 29, 41, 8, paint);
        }

        private void drawBus(Canvas c) {
            c.drawRoundRect(new RectF(9, 7, 39, 37), 5, 5, paint);
            c.drawLine(12, 22, 36, 22, paint);
            c.drawLine(24, 9, 24, 22, paint);
            c.drawCircle(16, 37, 3, paint);
            c.drawCircle(32, 37, 3, paint);
            c.drawLine(15, 28, 18, 28, paint);
            c.drawLine(29, 28, 32, 28, paint);
        }

        private void drawPhone(Canvas c) {
            c.drawRoundRect(new RectF(14, 4, 34, 44), 4, 4, paint);
            c.drawLine(20, 9, 28, 9, paint);
            c.drawCircle(24, 39, 1.2f, paint);
            c.drawCircle(32, 17, 5, paint);
            c.drawLine(32, 14, 32, 20, paint);
            c.drawLine(29, 17, 35, 17, paint);
        }

        private void drawWallet(Canvas c) {
            c.drawRoundRect(new RectF(6, 12, 42, 38), 4, 4, paint);
            c.drawLine(9, 12, 34, 7, paint);
            c.drawRoundRect(new RectF(27, 20, 42, 32), 3, 3, paint);
            c.drawCircle(32, 26, 1, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(7);
            c.drawText("Rs", 12, 29, paint);
            paint.setStyle(Paint.Style.STROKE);
        }

        private void drawFood(Canvas c) {
            c.drawLine(13, 8, 13, 18, paint);
            c.drawLine(9, 8, 9, 15, paint);
            c.drawLine(17, 8, 17, 15, paint);
            c.drawLine(9, 15, 17, 15, paint);
            c.drawLine(13, 18, 13, 41, paint);
            c.drawOval(new RectF(27, 8, 36, 21), paint);
            c.drawLine(31, 20, 31, 41, paint);
        }

        private void drawCar(Canvas c) {
            Path p = new Path();
            p.moveTo(8, 29); p.lineTo(12, 20); p.lineTo(19, 16);
            p.lineTo(30, 16); p.lineTo(37, 22); p.lineTo(40, 29);
            p.lineTo(40, 34); p.lineTo(8, 34); p.close();
            c.drawPath(p, paint);
            c.drawLine(17, 18, 15, 27, paint);
            c.drawLine(30, 18, 34, 27, paint);
            c.drawCircle(15, 35, 3, paint);
            c.drawCircle(33, 35, 3, paint);
        }

        private void drawRequest(Canvas c) {
            c.drawCircle(16, 14, 5, paint);
            c.drawArc(new RectF(7, 22, 25, 40), 180, 180, false, paint);
            c.drawCircle(33, 27, 9, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(8);
            c.drawText("Rs", 27, 30, paint);
            paint.setStyle(Paint.Style.STROKE);
        }

        private void drawSchool(Canvas c) {
            Path roof = new Path();
            roof.moveTo(7, 18); roof.lineTo(24, 8); roof.lineTo(41, 18);
            c.drawPath(roof, paint);
            c.drawRect(11, 19, 37, 38, paint);
            c.drawLine(16, 23, 16, 34, paint);
            c.drawLine(24, 23, 24, 34, paint);
            c.drawLine(32, 23, 32, 34, paint);
            c.drawLine(8, 40, 40, 40, paint);
        }
    }
    private TextView text(String value, int sizeSp, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(sizeSp);
        view.setTextColor(color);
        if (bold) {
            view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }
        return view;
    }

    private GradientDrawable roundedBackground(int color, int radiusDp) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radiusDp));
        return drawable;
    }

    private GradientDrawable topRoundedBackground(int color, int radiusPx) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadii(new float[]{radiusPx, radiusPx, radiusPx, radiusPx,
                0, 0, 0, 0});
        return drawable;
    }

    private GradientDrawable roundedBackground(int[] colors, int radiusDp) {
        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR, colors);
        drawable.setCornerRadius(dp(radiusDp));
        return drawable;
    }

    private GradientDrawable circleBackground(int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setShape(GradientDrawable.OVAL);
        return drawable;
    }

    private int tint(int color, float ratio) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.rgb((int) (255 + (red - 255) * ratio),
                (int) (255 + (green - 255) * ratio),
                (int) (255 + (blue - 255) * ratio));
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
