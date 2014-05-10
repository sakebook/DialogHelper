android-library-dialog-DialogHelper
===================================

Easy way to make dialog for everyone.

## 概要
 * DialogFragmentを一行で呼び出せます。独自のレイアウトにも対応。2.3以降対応。

## 取り込み方
 * 丸ごとimportし、Library Projectとして利用してください。

## 使い方
 * SimpleDialog
   * Chain method

```
DialogHelper.create(this)
    .setTitle("title")
    .setMessage("message")
    .setPositive("label")
    .setTouchCancelable(true)
    .build();
```

* CustomDialog
  * Chain method

```
DialogHelper.customCreate(this)
    .setLayout(R.layout.layoutId)
    .setEventList(getEventList())
    .setBackCancelable(true)
    .build();
```


  * ~~SimpleDialog~~

   * ~~第3~5引数はボタンのラベルになる。~~


```
 DialogHelper.callDialog(this, "title", "message", "positive", "negative", "neutral", "tag");

```

 * ~~CustomDialog~~
   * ~~第2引数は利用したいレイアウトのリソースID, 第3引数はイベントハンドリングしたいViewのIDを詰めたList~~

```
 DialogHelper.callCustomDialog(this, R.layout.layoutId, getEventList(), "tag");
```

## サンプル
* SimpleDialog

```
public class MainActivity extends FragmentActivity implements SimpleDialogsListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DialogHelper.callDialog(this, "title", "message", "positive", "negative", "neutral", "tag");
	}

	@Override
	public void simplePositiveClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

	@Override
	public void simpleNegativeClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

	@Override
	public void simpleNeutralClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

	@Override
	public void simpleCancel(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}
}
```

* CustomDialog

```
public class MainActivity extends FragmentActivity implements CustomDialogsListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DialogHelper.callCustomDialog(this, R.layout.layoutId, getEventList(), null);
  }

  @Override
  public void customClick(int id, View v) {
    // TODO Auto-generated method stub

  }

  @Override
  public void customCancel(int id) {
    // TODO Auto-generated method stub

  }

  private ArrayList<Integer> getEventList() {
        ArrayList<Integer> eventList = new ArrayList<Integer>();
        eventList.add(R.id.button1);
        eventList.add(R.id.button2);
        eventList.add(R.id.button3);

    return eventList;
  }

}

```

## License

```
Copyright 2014 Shinya Sakemoto.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
