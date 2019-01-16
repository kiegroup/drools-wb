import * as AppFormer from "appformer-js";
import * as HomeApi from "kie-wb-common-home-api";
import { Profile } from "@kiegroup-ts-generated/kie-wb-common-profile-api";

export class DroolsWbHomeScreenProvider implements HomeApi.HomeScreenProvider {
    public get(profile: Profile): HomeApi.HomeScreen {
        const welcomeText = "Welcome to KIE Workbench";

        const description =
            "KIE Workbench offers a set of flexible tools, that support the way " +
            "you need to work. Select a tool below to get started.";

        const backgroundImageUrl = "images/home_bg.jpg";

        const cards = [this.designCard(), this.devOpsCard()];

        return new HomeApi.HomeScreen(welcomeText, description, backgroundImageUrl, cards);
    }

    private designCard() {
        const cssClasses = ["pficon", "pficon-blueprint"];
        const title = "Design";
        const description = new HomeApi.CardDescriptionBuilder("Create and modify {0} and {1}.")
            .addLink("projects ", "LibraryPerspective")
            .addLink("pages", "ContentManagerPerspective")
            .build();

        return new HomeApi.Card(cssClasses, title, description, "LibraryPerspective");
    }

    private devOpsCard() {
        const cssClasses = ["fa", "fa-gears"];
        const title = "DevOps";
        const description = new HomeApi.CardDescriptionBuilder("Administer {0} and {1}.")
            .addLink("provisioning ", "ProvisioningManagementPerspective")
            .addLink("servers", "ServerManagementPerspective")
            .build();

        return new HomeApi.Card(cssClasses, title, description, "ServerManagementPerspective");
    }
}

AppFormer.register(new HomeApi.HomeScreenAppFormerComponent(new DroolsWbHomeScreenProvider()));
