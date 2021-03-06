package co.micol.prj.exe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.micol.prj.service.MemberService;
import co.micol.prj.service.MemberVO;
import co.micol.prj.serviceImpl.MemberServiceImpl;

public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memberService = new MemberServiceImpl();

	private void menuTitle() {
		System.out.println("=================");
		System.out.println("=== 멤 버 관 리 ===");
		System.out.println("== 1. 전체목록 조회");
		System.out.println("== 2. 회원 조회 ");
		System.out.println("== 3. 회원 등록 ");
		System.out.println("== 4. 회원정보 수정 ");
		System.out.println("== 5. 회원정보 삭제 ");
		System.out.println("== 6. 종 료 ");
		System.out.println("원하는 작업 번호를 선택하세요.");
	}

	public void run() {
		while (true) {
			menuTitle();
			int key = sc.nextInt();
			sc.nextLine();
			switch (key) {
			case 1:
				selectMemberList();
				break;
			case 2:
				selectMember();
				break;
			case 3:
				insertMember();
				break;
			case 4:
				updateMember();
				break;
			case 5:
				deleteMember();
				break;
			case 6:
				sc.close();
				return;

			}
		}
	}

	private void deleteMember() {
		MemberVO vo = new MemberVO();
		System.out.println("=========================");
		System.out.println("삭제할 회원 아이디를 입력하세요.");
		System.out.println("=========================");
		vo.setId(sc.nextLine());
		int n = memberService.deleteMember(vo);
		if (n != 0) {
			System.out.println("=====================");
			System.out.println("정상적으로 삭제되었습니다.");
			System.out.println("아무 키나 입력하세요...");
		} else {
			System.out.println("==================");
			System.out.println("삭제를 실패하였습니다.");
			System.out.println("아무 키나 입력하세요...");
		}
		sc.nextLine();
	}

	private void updateMember() {
		MemberVO vo = new MemberVO();
		boolean b = false;
		while (!b) {
			System.out.println("===========================");
			System.out.println("변경할 회원의 아이디를 입력하세요.");
			System.out.println("===========================");
			vo.setId(sc.nextLine());
			// 회원 존재 여부 확인하기
			vo = memberService.selectMember(vo);
			if (vo.getName() != null) {
				b = true;
			} else {
				System.out.println("존재하지 않는 아이디입니다.");
			}
		}

		System.out.println("새로운 이름을 입력하세요.");
		vo.setName(sc.nextLine());
		System.out.println("새로운 패스워드를 입력하세요.");
		vo.setPassword(sc.nextLine());
		System.out.println("새로운 연락처를 입력하세요.");
		vo.setTel(sc.nextLine());
		System.out.println("새로운 주소를 입력하세요.");
		vo.setAddress(sc.nextLine());
		System.out.println("새로운 권한을 입력하세요(ADMIN or USER)");
		vo.setAuthor(sc.nextLine());
		int n = memberService.updatetMember(vo);
		if (n != 0) {
			System.out.println("정상적으로 수정되었습니다.");
		} else {
			System.out.println("데이터 변경에 실패하였습니다.");
		}
		System.out.println("Press Enter Key...");
		sc.nextLine();
	}

	private void insertMember() {
		MemberVO vo = new MemberVO();
		System.out.println("=========================");
		System.out.println("회원정보를 등록하는 메뉴입니다.");
		System.out.println("=========================");
		boolean b = false;
		while (!b) { // 아이디 중복 확인
			System.out.println("아이디를 입력하세요.");
			vo.setId(sc.nextLine());
			vo = memberService.selectMember(vo);
			if (vo.getName() != null) {
				System.out.println("이미 존재하는 아이디입니다.");
				vo = new MemberVO();
			} else {
				System.out.println("사용 가능한 아이디입니다.");
				b = true;
			}
		}
			System.out.println("이름을 입력하세요.");
			vo.setName(sc.nextLine());
			System.out.println("패스워드를 입력하세요.");
			vo.setPassword(sc.nextLine());
			System.out.println("연락처를 입력하세요.");
			vo.setTel(sc.nextLine());
			System.out.println("주소를 입력하세요.");
			vo.setAddress(sc.nextLine());
			System.out.println("권한을 입력하세요(ADMIN or USER)");
			vo.setAuthor(sc.nextLine());
			int n = memberService.insertMember(vo);
			if (n != 0) {
				System.out.println("정상적으로 등록되었습니다.");
			} else {
				System.out.println("등록에 실패하였습니다.");
			}
			System.out.println("Press Enter Key...");
			sc.nextLine();
		}

	

	private void selectMember() {
		MemberVO vo = new MemberVO();
		System.out.println("=========================");
		System.out.println("검색할 회원 아이디를 입력하세요.");
		System.out.println("=========================");
		vo.setId(sc.nextLine());
		vo = memberService.selectMember(vo);
		vo.toString();
		System.out.println("====================");
		System.out.println("Enter를 누르세요...");
		sc.nextLine();

	}

	private void selectMemberList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = memberService.selectMemberList();
		System.out.println("=================");
		System.out.println("== 회원 목록 정보 ==");
		for (MemberVO vo : list) {
			vo.toString();
		}
		System.out.println("==================");
		System.out.println("Press Enter Key...");
		sc.nextLine();
	}
}
