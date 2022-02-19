package dev.lyze.textinput;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import dev.lyze.textinput.natives.INativeStuff;

public class Main extends ApplicationAdapter {
	private final ScreenViewport viewport = new ScreenViewport();
	private final INativeStuff nativeStuff;

	private Stage stage;

	public Main(INativeStuff nativeStuff) {
		this.nativeStuff = nativeStuff;
	}

	@Override
	public void create() {
		VisUI.load(VisUI.SkinScale.X2);

		viewport.setUnitsPerPixel(nativeStuff.getDensity());
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);

		VisTable table = new VisTable();
		table.setFillParent(true);

		table.add(new VisLabel("Numbers only: "));
		GwtTextInput inputField = new GwtTextInput(nativeStuff);
		// Example to showcase that filters work as well
		inputField.setTextFieldFilter(new TextField.TextFieldFilter() {
			private final char[] accepted = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
			@Override
			public boolean acceptChar(TextField textField, char c) {
				for (char ch : accepted) {
					if (ch == c)
						return true;
				}

				return false;
			}
		});
		table.add(inputField).width(300);

		stage.addActor(table);
	}

	@Override
	public void render() {
		ScreenUtils.clear(Color.PURPLE);

		stage.getViewport().apply();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}