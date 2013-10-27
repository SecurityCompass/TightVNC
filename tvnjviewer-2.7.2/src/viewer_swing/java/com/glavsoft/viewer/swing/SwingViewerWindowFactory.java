package com.glavsoft.viewer.swing;

import com.glavsoft.rfb.protocol.Protocol;
import com.glavsoft.rfb.protocol.ProtocolSettings;
import com.glavsoft.viewer.ConnectionPresenter;
import com.glavsoft.viewer.UiSettings;
import com.glavsoft.viewer.Viewer;

/**
 * @author dime at tightvnc.com
 */
public class SwingViewerWindowFactory {

    private final boolean isSeparateFrame;
    private final boolean isApplet;
    private final Viewer viewer;

    public SwingViewerWindowFactory(boolean isSeparateFrame, boolean isApplet, Viewer viewer) {
        this.isSeparateFrame = isSeparateFrame;
        this.isApplet = isApplet;
        this.viewer = viewer;
    }

    public SwingViewerWindow createViewerWindow(Protocol workingProtocol,
                                                ProtocolSettings rfbSettings, UiSettings uiSettings,
                                                String connectionString, ConnectionPresenter presenter) {
        Surface surface = new Surface(workingProtocol, uiSettings.getScaleFactor(), uiSettings.getMouseCursorShape());
        final SwingViewerWindow viewerWindow = new SwingViewerWindow(workingProtocol, rfbSettings, uiSettings,
                surface, isSeparateFrame, isApplet, viewer, connectionString, presenter);
        surface.setViewerWindow(viewerWindow);
        viewerWindow.setRemoteDesktopName(workingProtocol.getRemoteDesktopName());
        rfbSettings.addListener(viewerWindow);
        uiSettings.addListener(surface);
        return viewerWindow;
    }

}
