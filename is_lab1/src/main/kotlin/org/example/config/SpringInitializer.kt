package org.example.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class SpringInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    /**
     * тут пока можно ничего не писать
     */
    override fun getRootConfigClasses(): Array<Class<*>> =
        emptyArray()

    /**
     * регистрация контекста (отдаем главный конфиг класс)
     */
    override fun getServletConfigClasses(): Array<Class<*>> =
        arrayOf(ApplicationConfig::class.java)

    /**
     * какие URL-адреса спринг обрабатывает (DispatcherServlet)
     */
    override fun getServletMappings(): Array<String> =
        arrayOf("/")
}
