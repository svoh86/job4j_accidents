package ru.job4j.accidents.filter;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Класс подключает DelegatingFilterProxy - главный фильтр,
 * в котором идёт обработка запросов.
 * Без него не будет вызываться начальная форма регистрации
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
}
