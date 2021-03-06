package global.sesoc.board.chatHandler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import global.sesoc.board.vo.member;

public class chatInterceptor extends HttpSessionHandshakeInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(chatInterceptor.class);

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {

		logger.info("beforeHandshake : 소켓 보내는 중...");
		ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
		HttpSession session = servletRequest.getServletRequest().getSession();
		member member = (member) session.getAttribute("member");
		String username = member.getUsername();
		logger.info(username);
		attributes.put("username", username);
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
}
