package com.sinse.shopadmin.product.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.exception.ProductColorException;
import com.sinse.shopadmin.common.exception.ProductException;
import com.sinse.shopadmin.common.exception.ProductImgException;
import com.sinse.shopadmin.common.exception.ProductSizeException;
import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.product.model.Color;
import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.model.ProductColor;
import com.sinse.shopadmin.product.model.ProductImg;
import com.sinse.shopadmin.product.model.ProductSize;
import com.sinse.shopadmin.product.model.Size;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;
import com.sinse.shopadmin.product.repository.ColorDAO;
import com.sinse.shopadmin.product.repository.ProdcutColorDAO;
import com.sinse.shopadmin.product.repository.ProductDAO;
import com.sinse.shopadmin.product.repository.ProductImgDAO;
import com.sinse.shopadmin.product.repository.ProductSizeDAO;
import com.sinse.shopadmin.product.repository.SizeDAO;
import com.sinse.shopadmin.product.repository.SubCategoryDAO;
import com.sinse.shopadmin.product.repository.TopCategoryDAO;

public class ProductPage extends Page {
	JLabel la_topcategory, la_subcategory, la_product_name, la_brand, la_price;
	JLabel la_discount, la_color, la_size, la_introduce, la_detail;
	JButton bt_open, bt_regist, bt_list;

	JComboBox<TopCategory> cb_topcategory;
	JComboBox<SubCategory> cb_subcategory;

	JTextField t_product_name, t_brand, t_price, t_discount;
	JPanel p_preview;
	JTextArea t_introduce, t_detail;

	TopCategoryDAO topCategoryDAO;
	SubCategoryDAO subCategoryDAO;
	ColorDAO colorDAO;
	SizeDAO sizeDAO;
	ProductDAO productDAO;
	ProdcutColorDAO productColorDAO;
	ProductSizeDAO productSizeDAO;
	ProductImgDAO productImgDAO;

	JFileChooser chooser;

	int topcategory_id = 0;

	JList t_color;
	JList t_size;

	JScrollPane scroll_color;
	JScrollPane scroll_size;

	File[] files; // 유저가 선택한 파일 정보를 가진배열
					// 파일 inptStream,FIleOutpuyStream의 대상을 file이기때문이다.
	Image[] imgArray; // 유저가 선택한 이미지 정보를 가진 배열

	File[] newFiles; // uploadDialog에 의해 업로드 된 파일에 대한 정보를 담아놓자!!!
	DBManager db;

	public ProductPage(AppMain appMain) {
		super(appMain);
		setBackground(java.awt.Color.CYAN);

		// 컴포넌트 생성
		la_topcategory = new JLabel("최상위 카테고리");
		la_subcategory = new JLabel("하위 카테고리");
		la_product_name = new JLabel("상품명");
		la_brand = new JLabel("브랜드");
		la_price = new JLabel("가격");
		la_discount = new JLabel("할인률");
		la_color = new JLabel("색상");
		la_size = new JLabel("사이즈");
		bt_open = new JButton("상품사진 등록");
		la_introduce = new JLabel("상품 소개");
		la_detail = new JLabel("상세설명");

		cb_topcategory = new JComboBox<>();
		cb_subcategory = new JComboBox<>();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_discount = new JTextField();
		t_color = new JList<>();
		t_size = new JList<>();
		scroll_color = new JScrollPane(t_color);
		scroll_size = new JScrollPane(t_size);
		p_preview = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				// 유저가 선택한 파일 수 만큼 반복하면서 이미지를 그려주자
				if (imgArray != null) {
					for (int i = 0; i < imgArray.length; i++) {
						g.drawImage(imgArray[i], 5 + (i * 50), 5, 45, 45, appMain);
					}
				}
			}
		};
		t_introduce = new JTextArea();
		t_detail = new JTextArea();
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록");

		topCategoryDAO = new TopCategoryDAO();
		subCategoryDAO = new SubCategoryDAO();
		colorDAO = new ColorDAO();
		sizeDAO = new SizeDAO();
		productDAO = new ProductDAO();
		productColorDAO = new ProdcutColorDAO();
		productSizeDAO = new ProductSizeDAO();
		productImgDAO = new ProductImgDAO();
		db = DBManager.getInstance();

		chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		// 스타일 설정
		Dimension d = new Dimension(400, 30);
		JLabel[] labels = { la_topcategory, la_subcategory, la_product_name, la_brand, la_price, la_discount, la_color,
				la_size, la_introduce, la_detail };
		for (JLabel label : labels) {
			label.setPreferredSize(d);
		}

		cb_topcategory.setPreferredSize(d);
		cb_subcategory.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_discount.setPreferredSize(d);
		t_color.setPreferredSize(d);
		t_size.setPreferredSize(d);
		bt_open.setPreferredSize(d);
		p_preview.setPreferredSize(new Dimension(400, 80));
		t_introduce.setPreferredSize(new Dimension(400, 50));
		t_detail.setPreferredSize(new Dimension(460, 60));
		bt_regist.setPreferredSize(new Dimension(130, 30));
		bt_list.setPreferredSize(new Dimension(130, 30));

		// 조립
		add(la_topcategory);
		add(cb_topcategory);
		add(la_subcategory);
		add(cb_subcategory);
		add(la_product_name);
		add(t_product_name);
		add(la_brand);
		add(t_brand);
		add(la_price);
		add(t_price);
		add(la_discount);
		add(t_discount);
		add(la_color);
		add(scroll_color);
		add(la_size);
		add(scroll_size);
		add(bt_open);
		add(p_preview);
		add(la_introduce);
		add(t_introduce);
		add(la_detail);
		add(t_detail);
		add(bt_regist);
		add(bt_list);

		setPreferredSize(new Dimension(880, 750));

		// 이벤트 연결
		cb_topcategory.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					TopCategory selected = (TopCategory) cb_topcategory.getSelectedItem();
					getSubCategory(selected);
				}
			}
		});

		// file finder
		bt_open.addActionListener(e -> {
			int result = chooser.showOpenDialog(ProductPage.this);

			if (result == JFileChooser.APPROVE_OPTION) {
				preview();
			}
		});

		bt_regist.addActionListener(e -> {
			regist();
		});

		// 초기 데이터 로딩
		getTopCategory();
		getColorList();
		getSizeList();
	}

	public void preview() {
		// 유저가 선택한 파일에 대한 정보를 얻어와야함.
		files = chooser.getSelectedFiles();

		if (files.length > 6) {
			// 이미지 등록수는 6개
			JOptionPane.showMessageDialog(this, "이미지는 최대 6장까지만 가능합니다.");
		} else {

			imgArray = new Image[files.length]; // 유저가 선택한 파일의수에 맞게 이미지 배열이 준비되어야한다.
			// 파일은 파일일 뿐.. 이미지가 아니다. 따라서 파일을 이용하여 이미지를 만들자.

			for (int i = 0; i < files.length; i++) {
				BufferedImage bufferImg;
				try {
					bufferImg = ImageIO.read(files[i]);
					imgArray[i] = bufferImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			p_preview.repaint();
		}
	}

	public void getSizeList() {
		t_size.setListData(new Vector(sizeDAO.selectAll()));

	}

	public void getColorList() {
		t_color.setListData(new Vector(colorDAO.selectAll()));

	}

	public void getSubCategory(TopCategory selected) {
		List<SubCategory> sublist;
		try {
			sublist = subCategoryDAO.select(selected);

			// 모든하위 카테고리 콤보 박스 지우기
			cb_subcategory.removeAllItems();
			SubCategory dummy = new SubCategory();
			dummy.setSub_name("하위카테고리를 선택하세요.");
			dummy.setSubcategory_id(0);
			cb_subcategory.addItem(dummy);
			for (int i = 0; i < sublist.size(); i++) {
				SubCategory subCategory = sublist.get(i);
				cb_subcategory.addItem(subCategory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getTopCategory() {
		List<TopCategory> toplist = topCategoryDAO.selectAll();

		TopCategory dummy = new TopCategory();
		dummy.setTop_name("상위 카테고리를 선택하세요.");
		dummy.setTopcategory_id(0);
		cb_topcategory.addItem(dummy);

		for (TopCategory tc : toplist) {
			cb_topcategory.addItem(tc);
		}
	}

	public void getSubCategory(int topcategory_id) {
		cb_subcategory.removeAllItems();

		String sql = "SELECT * FROM subcategory WHERE topcategory_id = ?";
		DBManager db = DBManager.getInstance();
		Connection con = db.getConnetion();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, topcategory_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SubCategory sc = new SubCategory();
				sc.setSubcategory_id(rs.getInt("subcategory_id"));
				sc.setSub_name(rs.getString("sub_name"));
				cb_subcategory.addItem(sc);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void upload() {
		// 시각적 효과를 위해 각 이미지의 업로드 진행률을 보여주자.
		// 새창을 띄워서 보여주자.
		UploadDialog dialog = new UploadDialog(this);
		// modal을 이용하여 불안전한 데이터를 처리할 수 있다.
		System.out.println("여기실행");
	}

	public void insert() {
		Connection con = db.getConnetion();
		try {
			con.setAutoCommit(false);

			// 이 영역은 트랜직션이 실질적으로 수행될 영역이다.
			// 만약 이 영역에서 에러가 발생하면 실행부가 catch문으로 진행하기 떄문에
			// 해당 캐치문에서 모든 트랜직션을 rollback 시키면 된다!
			// ProductDAO 에게 일을 시키자...

			// product 모델 인스턴스 1개를 만들어, 안에다가
			// 상품 등록 폼에 데이터를 채워넣자!!(Setter)
			Product product = new Product();
			product.setProduct_name(t_product_name.getText());
			product.setBrand(t_brand.getText());
			product.setPrice(Integer.parseInt(t_price.getText()));
			product.setDiscount(Integer.parseInt(t_discount.getText()));
			product.setIntroduce(t_introduce.getText());
			product.setDetail(t_detail.getText());

			// fk는 어떻게 받아올것인가?
			product.setSubcategory((SubCategory) cb_subcategory.getSelectedItem());

			productDAO.insert(product);
			int product_id = productDAO.selectRecentPK();
			// 구해온 최신 pk를 product에 반영한다
			product.setProduct_id(product_id);
			System.out.println("등록결과 " + product_id);

			List<Color> colorList = t_color.getSelectedValuesList();

			ProductColor productColor = new ProductColor();
			for (Color color : colorList) {
				productColor.setProduct(product);
				productColor.setColor(color);
				productColorDAO.insert(productColor);
			}

			// 상품에 딸려있는 사이즈를 DB에 등록하는 과정이다.
			List<Size> sizeList = t_size.getSelectedValuesList();
			ProductSize productsize = new ProductSize(); // 현재는 빈 상태
			for (Size size : sizeList) {
				productsize.setProduct(product);
				productsize.setSize(size);
				productSizeDAO.insert(productsize);
			}

			// 상품에 딸려있는 이미지를 DB에 등록해보자.
			ProductImg productimg = new ProductImg();
			for (int i = 0; i < newFiles.length; i++) {
				File file = newFiles[i];
				productimg.setProduct(product);
				productimg.setFilename(file.getName());
				productImgDAO.insert(productimg);

			}
			con.commit();
		} catch (ProductException | ProductColorException | ProductSizeException | ProductImgException e) {
			e.printStackTrace(); //개발자를 위한 에러 로그
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//사용자를 위해 에러 원인을 알려주자
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	// 이미지가 포함된 업로드 및 DB 에 연동까지 진행해야함.
	public void regist() {
		// upload();

		// 유효성 체크 필수..!!
		// 상위 카테고리의 유효성을 체크한다.
		// 0번째를 선택했다는 것은 카테고리를 선택하지 않은 것이다.
		if (cb_topcategory.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "상의 카테고리를 고르시오.");
		} else if (cb_subcategory.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "하의 카테고리를 고르시오.");
		} else if (t_product_name.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "하의 카테고리를 고르시오.");
		} else if (t_brand.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "브랜드를 입력하세요.");
		} else if (t_price.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "가격 입력하세요.");
		} else if (t_discount.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "할인 입력하세요.");
		} else if (t_color.getMinSelectionIndex() == -1) {
			JOptionPane.showMessageDialog(this, "1개 이상의 색상 선택 하세요.");
		} else if (t_size.getMinSelectionIndex() == -1) {
			JOptionPane.showMessageDialog(this, "1개 이상의 사이즈 선택 하세요.");
		} else if (files.length < 1) {
			JOptionPane.showMessageDialog(this, "상품이미지를 선택하세요.");
		} else if (t_introduce.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "상품소개를 입력하세요.");
		} else {
			// 유효성 체크를 모두 통과했다면 업로드를 진행한다.
			upload();
			insert();// mysql에 입력한 값을 넣으러간다.

		}

	}
}
