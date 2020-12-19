package modelo.vo;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class CursanVO {

	private List<CursaVO> cursan;

	public List<CursaVO> getCursan() {
		return cursan;
	}
	
	@XmlElement(name="cursa")
	public void setCursan(List<CursaVO> cursan) {
		this.cursan = cursan;
	}		
}
