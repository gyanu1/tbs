package mum.cs490.tbs.report;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

public enum HeaderStyles {
	Header1(1), Header2(2), Header3(3), TitleHeader(4);

	private int type;

	private HeaderStyles(int n) {
		this.type = n;
	}

	private StyleBuilder getHeader1(Header header) {
		StyleBuilder style = stl.style();
		style.setFontName(header.getFontName() == null ? "Arial" : header.getFontName());
		style.setFontSize(14);
		style.setBottomPadding(5);
		style.setTopPadding(10);
		style.bold();
//		style.setForegroudColor(header.getColor() != null ? Color.decode(header.getColor()) : Color
//				.decode("#000000"));
		style.setHorizontalAlignment(header.getHorizontalAlignment() != null ? header
				.getHorizontalAlignment() : HorizontalAlignment.LEFT);
		return style;
	}

	private StyleBuilder getHeader2(Header header) {
		StyleBuilder style = stl.style();
		style.setFontName(header.getFontName() == null ? "Arial" : header.getFontName());
		style.setFontSize(18);
		style.boldItalic();
		style.setBottomPadding(5);
		style.setTopPadding(10);
//		style.setForegroudColor(header.getColor() != null ? Color.decode(header.getColor()) : Color
//				.decode("#000000"));
		style.setHorizontalAlignment(header.getHorizontalAlignment() != null ? header
				.getHorizontalAlignment() : HorizontalAlignment.LEFT);
		return style;
	}

	private StyleBuilder getHeader3(Header header) {
		StyleBuilder style = stl.style();
		style.setFontName(header.getFontName() == null ? "Arial" : header.getFontName());
		style.setFontSize(22);
		style.setBottomPadding(5);
		style.setTopPadding(10);
		style.boldItalic();
//		style.setForegroudColor(header.getColor() != null ? Color.decode(header.getColor()) : Color
//				.decode("#000000"));
		style.setHorizontalAlignment(header.getHorizontalAlignment() != null ? header
				.getHorizontalAlignment() : HorizontalAlignment.LEFT);
		return style;
	}

	public StyleBuilder getHeaderStyle(Header header) {
		StyleBuilder styleBuilder = null;
		switch (type) {
		case 1:
			styleBuilder = getHeader1(header);
			break;
		case 2:
			styleBuilder = getHeader2(header);
			break;
		case 3:
			styleBuilder = getHeader3(header);
			break;
		case 4:
			styleBuilder = getTitleHeader();
			break;
		default:
			break;
		}
		return styleBuilder;

	}

	public StyleBuilder getTitleHeader() {
		StyleBuilder style = stl.style();
		style.setFont(stl.fontArial());
		style.setFontSize(10);
		style.setBottomPadding(10);
		style.setTopPadding(10);
		style.bold();
		style.setHorizontalAlignment(HorizontalAlignment.CENTER);
		return style;
	}
}
