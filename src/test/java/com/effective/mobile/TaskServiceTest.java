package com.effective.mobile;

import com.effective.mobile.converter.TaskConverter;
import com.effective.mobile.converter.UserConverter;
import com.effective.mobile.data.entity.Task;
import com.effective.mobile.data.entity.User;
import com.effective.mobile.data.entity.enums.Priority;
import com.effective.mobile.data.entity.enums.Role;
import com.effective.mobile.data.entity.enums.Status;
import com.effective.mobile.data.repository.TaskRepository;
import com.effective.mobile.data.repository.UserRepository;
import com.effective.mobile.model.dto.task.TaskResponseDto;
import com.effective.mobile.model.dto.task.request.AssignExecutorRequestDto;
import com.effective.mobile.model.dto.task.request.TaskRequestDto;
import com.effective.mobile.security.UserDetails;
import com.effective.mobile.service.task.TaskService;
import com.effective.mobile.service.task.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceTest {
	private TaskService taskService;
	@InjectMocks
	private UserConverter userConverter;

	@Mock
	private TaskConverter taskConverter;

	@Mock
	private UserRepository userRepository;

	@Mock
	private TaskRepository taskRepository;

	final User admin = User.builder()
			.id(1L)
			.createdAt(LocalDateTime.of(2024, 10, 5, 15, 25))
			.firstName("admin")
			.lastName("admin")
			.email("admin@example.com")
			.role(Role.ROLE_ADMIN)
			.password("$2y$10$IhpwEMgV6XdmXPEuGBMHwOSJMIAbaDvsVS6Z6XDIYZfHlB/opvRni")
			.build();

	final User user = User.builder()
			.id(2L)
			.createdAt(LocalDateTime.of(2024, 11, 23, 10, 25))
			.firstName("user")
			.lastName("user")
			.email("user@example.com")
			.role(Role.ROLE_USER)
			.password("2y$10$PfrnCtZ2EbvrwPdas1C5Wen98WGLNXPbejNperOL76VdzNOEAS6NC")
			.build();

	@BeforeEach
	void setUp() {
		taskService = new TaskServiceImpl(taskConverter, userRepository, taskRepository);

		// Настраиваем SecurityContext
		SecurityContext securityContext = mock(SecurityContext.class);
		Authentication authentication = mock(Authentication.class);

		// Возвращаем объект admin, который является User
		when(authentication.getPrincipal()).thenReturn(admin);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext); // Устанавливаем mock-контекст
	}

	/**
	 * Тестирование создания задачи
	 */
	@Test
	void createTaskTest() {
		final var taskRequestDto = TaskRequestDto.builder()
				.title("test")
				.description("test")
				.status(Status.IN_PROGRESS)
				.priority(Priority.MEDIUM)
				.executor_ids(Set.of(1L, 2L))
				.build();

		final var taskResponseDto = TaskResponseDto.builder()
				.id(1L)
				.title("test")
				.description("test")
				.status(Status.IN_PROGRESS)
				.priority(Priority.MEDIUM)
				.author(userConverter.toDto(admin))
				.commentList(null)
				.executorSet(Set.of(user, admin).stream().map(userConverter::toDto).collect(Collectors.toSet()))
				.build();

		when(userRepository.findAllByIds(Set.of(1L, 2L))).thenReturn(Set.of(user, admin));
		when(taskRepository.save(any(Task.class))).then(returnsFirstArg());
		when(UserDetails.getUser()).thenReturn(admin);
		when(taskService.create(taskRequestDto)).thenReturn(taskResponseDto);

		final var taskResDto = taskService.create(taskRequestDto);

		assertEquals(1L, taskResDto.getId());
		assertEquals("test", taskResDto.getTitle());
		assertEquals("test", taskResDto.getDescription());
		assertEquals(Status.IN_PROGRESS, taskResDto.getStatus());
		assertEquals(Priority.MEDIUM, taskResDto.getPriority());
		assertEquals(1L, taskResDto.getAuthor().getId());

	}

	/**
	 * Тестирование назначение исполнителей задачи
	 */
	@Test
	void assignExecutorTest() {
		Long taskId = 1L;
		final var assignExecutor = new AssignExecutorRequestDto(Set.of(1L));
		final var task = Task.builder()
				.id(1L)
				.title("test")
				.description("test")
				.status(Status.IN_PROGRESS)
				.priority(Priority.MEDIUM)
				.commentList(null)
				.executor(Set.of(user, admin))
				.build();

		final var taskResponseDto = TaskResponseDto.builder()
				.id(1L)
				.title("test")
				.description("test")
				.status(Status.IN_PROGRESS)
				.priority(Priority.MEDIUM)
				.author(userConverter.toDto(admin))
				.commentList(null)
				.executorSet(Set.of(admin).stream().map(userConverter::toDto).collect(Collectors.toSet()))
				.build();

		when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
		when(userRepository.findAllByIds(Set.of(1L))).thenReturn(Set.of(user, admin));
		when(taskRepository.save(any(Task.class))).then(returnsFirstArg());
		when(UserDetails.getUser()).thenReturn(admin);
		when(taskService.assignExecutor(taskId, assignExecutor)).thenReturn(taskResponseDto);

		final var taskResDto = taskService.assignExecutor(taskId, assignExecutor);

		assertEquals(1L, taskResDto.getId());
		assertEquals("test", taskResDto.getTitle());
		assertEquals("test", taskResDto.getDescription());
		assertEquals(Status.IN_PROGRESS, taskResDto.getStatus());
		assertEquals(Priority.MEDIUM, taskResDto.getPriority());
		assertEquals(1L, taskResDto.getAuthor().getId());
		assertTrue(taskResDto.getExecutorSet().stream()
				.anyMatch(userRespDto -> userRespDto.getId().equals(admin.getId())));
		assertEquals(1, taskResDto.getExecutorSet().size());

	}

}
