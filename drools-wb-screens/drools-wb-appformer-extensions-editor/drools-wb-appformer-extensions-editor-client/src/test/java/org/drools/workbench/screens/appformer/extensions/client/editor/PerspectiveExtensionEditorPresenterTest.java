package org.drools.workbench.screens.appformer.extensions.client.editor;

import org.drools.workbench.screens.appformer.extensions.client.type.PerspectiveExtensionResourceType;
import org.drools.workbench.screens.appformer.extensions.model.PerspectiveExtensionContent;
import org.drools.workbench.screens.appformer.extensions.service.PerspectiveExtensionService;
import org.jboss.errai.common.client.api.Caller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.callbacks.CommandDrivenErrorCallback;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.ext.layout.editor.api.LayoutServices;
import org.uberfire.ext.layout.editor.api.editor.LayoutTemplate;
import org.uberfire.ext.layout.editor.client.LayoutEditorPresenter;
import org.uberfire.ext.layout.editor.client.api.LayoutDragComponentGroup;
import org.uberfire.mocks.CallerMock;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PerspectiveExtensionEditorPresenterTest {

    PerspectiveExtensionEditorPresenter presenter;

    @Mock
    PerspectiveExtensionEditorView view;

    @Mock
    PerspectiveExtensionResourceType perspectiveExtensionResourceType;

    @Mock
    PerspectiveExtensionService perspectiveExtensionService;

    Caller<PerspectiveExtensionService> perspectiveExtensionServiceMock;

    @Mock
    LayoutServices layoutServices;

    Caller<LayoutServices> layoutServicesMock;

    @Mock
    LayoutEditorPresenter layoutEditorPresenter;
    private ObservablePath currentPath = mock(ObservablePath.class);

    @Before
    public void setup() {
        perspectiveExtensionServiceMock = new CallerMock<>(perspectiveExtensionService);
        layoutServicesMock = new CallerMock<>(layoutServices);
        presenter = spy(new PerspectiveExtensionEditorPresenter(view,
                                                                perspectiveExtensionResourceType,
                                                                perspectiveExtensionServiceMock,
                                                                layoutServicesMock,
                                                                layoutEditorPresenter) {
            @Override
            LayoutDragComponentGroup lookupPerspectiveDragComponents() {
                return mock(
                        LayoutDragComponentGroup.class);
            }

            @Override
            void resetEditorPages(PerspectiveExtensionContent content) {

            }

            @Override
            protected CommandDrivenErrorCallback getNoSuchFileExceptionErrorCallback() {
                return mock(CommandDrivenErrorCallback.class);
            }

            @Override
            ObservablePath getCurrentPath() {
                return currentPath;
            }
        });
    }

    @Test
    public void setupLayoutEditorTest() {
        presenter.setupLayoutEditor();
        verify(layoutEditorPresenter).clear();
        verify(layoutEditorPresenter).addDraggableComponentGroup(any());
        verify(layoutEditorPresenter).setPageStyle(LayoutTemplate.Style.PAGE);
    }

    @Test
    public void loadContentTest() {
        PerspectiveExtensionContent content = mock(PerspectiveExtensionContent.class);
        when(perspectiveExtensionService.loadContent(any())).thenReturn(content);

        presenter.loadContent();

        verify(view).showLoading();
        verify(presenter).resetEditorPages(content);
        verify(layoutEditorPresenter).loadLayout(content.getContent(),
                                                 view.emptyTitleText(),
                                                 view.emptySubTitleText());
        verify(view).hideBusyIndicator();
    }

    @Test
    public void loadContentNullPathTest() {
        currentPath = null;
        PerspectiveExtensionContent content = mock(PerspectiveExtensionContent.class);
        when(perspectiveExtensionService.loadContent(any())).thenReturn(content);

        presenter.loadContent();

        verify(view).showLoading();
        verify(presenter,
               never()).resetEditorPages(content);
        verify(layoutEditorPresenter,
               never()).loadLayout(content.getContent(),
                                   view.emptyTitleText(),
                                   view.emptySubTitleText());
        verify(view).hideBusyIndicator();
    }
}