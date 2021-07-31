package com.pizzaluc.milano;

<<<<<<< HEAD
import com.pizzaluc.milano.blocks.CounterBlock;
import com.pizzaluc.milano.blocks.CounterTileEntity;
import com.pizzaluc.milano.blocks.Uranium;
import com.pizzaluc.milano.items.UraniumItem;
=======
import com.pizzaluc.milano.blocks.UraniumOre;
import com.pizzaluc.milano.items.UraniumIngot;
>>>>>>> 29c0c26278f23401d205f78e19d76be29ce9d503
import com.pizzaluc.milano.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
<<<<<<< HEAD
import net.minecraft.loot.LootTable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
=======
>>>>>>> 29c0c26278f23401d205f78e19d76be29ce9d503
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.css.Counter;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("milano")
public class milano
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String MODID = "milano";

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);


    public static final RegistryObject<Block> COUNTER = BLOCKS.register("counter", CounterBlock::new);

    public static final RegistryObject<Block> URANIUM = BLOCKS.register("uranium", UraniumOre::new);

    public static final RegistryObject<Item> URANIUM_ITEM = ITEMS.register("uranium", () ->
                                                                                        new BlockItem(URANIUM.get(),
                                                                                                new Item.Properties().tab(ItemGroup.TAB_MATERIALS)
                                                                                        ));

    public static final RegistryObject<Item> COUNTER_ITEM = ITEMS.register("counter", () ->
            new BlockItem(COUNTER.get(),
                    new Item.Properties().tab(ItemGroup.TAB_MATERIALS)
            ));

    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", UraniumIngot::new);

    public static final RegistryObject<TileEntityType<CounterTileEntity>> COUNTER_TE = TILES.register("counter", () -> TileEntityType.Builder.of(CounterTileEntity::new, COUNTER.get()).build(null));



    public milano() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("milano", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");


        }
    }
}
